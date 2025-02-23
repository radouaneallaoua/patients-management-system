import React, { useState } from 'react';
import { Form, Input, DatePicker, Button, Select, Upload, Row, Col, Card, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';
import moment from 'moment';
import axios from 'axios';
const { Option } = Select;
const { TextArea } = Input;

const DiagnosticForm = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onFinish = async (values) => {
    setLoading(true);
    const payload = {
      ...values,
      date: values.date.format('YYYY-MM-DD'),
      traitements: values.traitements.map((traitement) => ({
        ...traitement,
        startsAt: traitement.startsAt.format('YYYY-MM-DD'),
        endsAt: traitement.endsAt.format('YYYY-MM-DD'),
        prescriptions: traitement.prescriptions.map((prescription) => ({
          ...prescription,
          date: prescription.date.format('YYYY-MM-DD'),
        })),
      })),
    };

    try {
      const response = await axios.post('http://localhost:8080/diagnostics', payload);
      if (response.status === 200) {
        message.success('Diagnostic created successfully!');
        form.resetFields();
      }
    } catch (error) {
      message.error('Failed to create diagnostic.');
    } finally {
      setLoading(false);
    }
  };

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e?.fileList;
  };

  return (
    <Card title="Create Diagnostic" bordered={false} style={{ boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)' }}>
      <Form form={form} layout="vertical" onFinish={onFinish}>
        <Row gutter={[16, 16]}>
          <Col span={24}>
            <h3>Diagnostic Information</h3>
          </Col>
          <Col span={12}>
            <Form.Item name="symptoms" label="Symptoms" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="condition" label="Condition" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="date" label="Date" rules={[{ required: true }]}>
              <DatePicker style={{ width: '100%' }} />
            </Form.Item>
          </Col>
          <Col span={24}>
            <Form.Item name="notes" label="Notes">
              <TextArea rows={4} />
            </Form.Item>
          </Col>
        </Row>

        <Row gutter={[16, 16]}>
          <Col span={24}>
            <h3>Traitement Information</h3>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'planDetails']} label="Plan Details" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'state']} label="State" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'startsAt']} label="Starts At" rules={[{ required: true }]}>
              <DatePicker style={{ width: '100%' }} />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'endsAt']} label="Ends At" rules={[{ required: true }]}>
              <DatePicker style={{ width: '100%' }} />
            </Form.Item>
          </Col>
        </Row>

        <Row gutter={[16, 16]}>
          <Col span={24}>
            <h3>Prescription Information</h3>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'prescriptions', 0, 'medications']} label="Medications" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'prescriptions', 0, 'status']} label="Status" rules={[{ required: true }]}>
              <Select>
                <Option value="status1">Status 1</Option>
                <Option value="status2">Status 2</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name={['traitements', 0, 'prescriptions', 0, 'date']} label="Date" rules={[{ required: true }]}>
              <DatePicker style={{ width: '100%' }} />
            </Form.Item>
          </Col>
          <Col span={24}>
            <Form.Item name={['traitements', 0, 'prescriptions', 0, 'note']} label="Note">
              <TextArea rows={4} />
            </Form.Item>
          </Col>
        </Row>

        <Row gutter={[16, 16]}>
          <Col span={24}>
            <h3>Attachments</h3>
          </Col>
          <Col span={24}>
            <Form.Item name="attachments" valuePropName="fileList" getValueFromEvent={normFile}>
              <Upload name="attachments" action="/upload" listType="picture">
                <Button icon={<UploadOutlined />}>Upload Attachments</Button>
              </Upload>
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={24}>
            <Form.Item>
              <Button type="primary" htmlType="submit" loading={loading}>
                Submit
              </Button>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </Card>
  );
};

export default DiagnosticForm;