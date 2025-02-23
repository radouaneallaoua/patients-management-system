import React, { useState, useEffect } from 'react';
import { Form, Input, DatePicker, Button, message, Select, Row, Col, Card, Spin } from 'antd';
import axios from 'axios';
import moment from 'moment';

const { Option } = Select;
const { TextArea } = Input;

const ConsultationForm = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [patients, setPatients] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const [isFetching, setIsFetching] = useState(false);

  useEffect(() => {
    fetchPatientsAndDoctors();
  }, []);

  const fetchPatientsAndDoctors = async () => {
    setIsFetching(true);
    try {
      const [patientsResponse, doctorsResponse] = await Promise.all([
        axios.get('http://localhost:8080/patients'), // Replace with your patient endpoint
        axios.get('http://localhost:8085/doctor'),  // Replace with your doctor endpoint
      ]);
      setPatients(patientsResponse.data);
      setDoctors(doctorsResponse.data);
    } catch (error) {
      message.error('Failed to fetch patients or doctors.');
    } finally {
      setIsFetching(false);
    }
  };

  const onFinish = async (values) => {
    setLoading(true);
    const consultationData = {
      ...values,
      timeStamp: values.timeStamp.format('YYYY-MM-DDTHH:mm:ss'),
    };

    try {
      const response = await axios.post('http://localhost:8089/consultation', consultationData);
      if (response.status === 200) {
        message.success('Consultation created successfully!');
        form.resetFields();
      }
    } catch (error) {
      message.error('Failed to create consultation.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Row justify="center" align="middle" style={{ minHeight: '100vh', background: '#f0f2f5' }}>
      <Col xs={24} sm={20} md={16} lg={12} xl={10}>
        <Card
          title="Create Consultation"
          bordered={false}
          style={{ boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)' }}
        >
          <Spin spinning={isFetching}>
            <Form form={form} layout="vertical" onFinish={onFinish}>
              <Form.Item
                label="Patient"
                name="patientId"
                rules={[{ required: true, message: 'Please select a patient!' }]}
              >
                <Select placeholder="Select a patient" loading={isFetching}>
                  {patients.map((patient) => (
                    <Option key={patient.id} value={patient.id}>
                      {patient.name} (ID: {patient.id})
                    </Option>
                  ))}
                </Select>
              </Form.Item>

              <Form.Item
                label="Doctor"
                name="DoctorId"
                rules={[{ required: true, message: 'Please select a doctor!' }]}
              >
                <Select placeholder="Select a doctor" loading={isFetching}>
                  {doctors.map((doctor) => (
                    <Option key={doctor.id} value={doctor.id}>
                      {doctor.name} (ID: {doctor.id})
                    </Option>
                  ))}
                </Select>
              </Form.Item>

              <Form.Item
                label="Timestamp"
                name="timeStamp"
                rules={[{ required: true, message: 'Please select the timestamp!' }]}
              >
                <DatePicker
                  showTime
                  format="YYYY-MM-DD HH:mm:ss"
                  style={{ width: '100%' }}
                  disabledDate={(current) => current && current < moment().startOf('day')}
                />
              </Form.Item>

              <Form.Item
                label="State"
                name="state"
                rules={[{ required: true, message: 'Please input the state!' }]}
              >
                <Input placeholder="Enter consultation state" />
              </Form.Item>
              <Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  loading={loading}
                  style={{ width: '100%', marginTop: '16px' }}
                >
                  Submit
                </Button>
              </Form.Item>
            </Form>
          </Spin>
        </Card>
      </Col>
    </Row>
  );
};

export default ConsultationForm;