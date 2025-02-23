import React, { useState, useEffect } from 'react';
import { Table, Button, Modal, Tag, DatePicker, Input, Select, Row, Col, Card, Spin } from 'antd';
import { useNavigate } from 'react-router-dom';
import { message } from 'antd';
import moment from 'moment';
import axios from 'axios';

const { Search } = Input;
const { Option } = Select;
const { RangePicker } = DatePicker;

const ConsultationDashboard = () => {
  const [fetchedData, setFetchedData] = useState([]);
  const [consultations, setConsultations] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedConsultation, setSelectedConsultation] = useState(null);
  const [filters, setFilters] = useState({
    patientId: null,
    doctorId: null,
    state: null,
    dateRange: null,
  });

  const navigate = useNavigate();

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    filterConsultations();
  }, [filters]);

  const filterConsultations = ()=>{
    setLoading(true);
    const filteredData = consultations.filter((consultation) => {
      const matchesPatientId = filters.patientId ? consultation.patientId.toString().includes(filters.patientId) : true;
      const matchesDoctorId = filters.doctorId ? consultation.DoctorId.toString().includes(filters.doctorId) : true;
      const matchesState = filters.state ? consultation.state === filters.state : true;
      const matchesDateRange = filters.dateRange
        ? moment(consultation.timeStamp).isBetween(filters.dateRange[0], filters.dateRange[1], 'day', '[]')
        : true;

      return matchesPatientId && matchesDoctorId && matchesState && matchesDateRange;
    });
    setConsultations(filteredData);
  } 

  const fetchData = async () => {
    try {
      setLoading(true); 
      const response = await axios.get('https://localhost:8089/consultation');
      setConsultations(response.data);
      setLoading(false);
    } catch (err) {
      console.log(err);
      setLoading(false); // Stop loading
    }
  };

  const showConsultationDetails = (consultation) => {
    setSelectedConsultation(consultation);
    setIsModalVisible(true);
  };

  const handleFilterChange = (key, value) => {
    setFilters({ ...filters, [key]: value });
  };

  const handleViewDiagnostic = (diagnosticId) => {
    navigate(`/diagnostics/${diagnosticId}`); // Navigate to diagnostic page
  };

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      sorter: (a, b) => a.id - b.id,
    },
    {
      title: 'Patient ID',
      dataIndex: 'patientId',
      key: 'patientId',
      sorter: (a, b) => a.patientId - b.patientId,
    },
    {
      title: 'Doctor ID',
      dataIndex: 'DoctorId',
      key: 'DoctorId',
      sorter: (a, b) => a.DoctorId - b.DoctorId,
    },
    {
      title: 'Timestamp',
      dataIndex: 'timeStamp',
      key: 'timeStamp',
      render: (text) => moment(text).format('YYYY-MM-DD HH:mm:ss'),
      sorter: (a, b) => moment(a.timeStamp).unix() - moment(b.timeStamp).unix(),
    },
    {
      title: 'State',
      dataIndex: 'state',
      key: 'state',
      render: (state) => (
        <Tag color={state === 'completed' ? 'green' : state === 'pending' ? 'orange' : 'red'}>
          {state}
        </Tag>
      ),
      filters: [
        { text: 'Pending', value: 'pending' },
        { text: 'Completed', value: 'completed' },
        { text: 'Cancelled', value: 'cancelled' },
      ],
      onFilter: (value, record) => record.state === value,
    },
    {
      title: 'Actions',
      key: 'actions',
      render: (_, record) => (
        <>
          <Button type="link" onClick={() => showConsultationDetails(record)}>
            View Details
          </Button>
          <Button type="link" onClick={() => handleViewDiagnostic(record.diagnosticId)}>
            View Diagnostic
          </Button>
        </>
      ),
    },
  ];

  return (
    <div style={{ padding: '24px', background: '#f0f2f5' }}>
      <Card title="Consultation Dashboard" bordered={false} style={{ boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)' }}>
        <Row gutter={[16, 16]} style={{ marginBottom: '24px' }}>
          <Col xs={24} sm={12} md={6}>
            <Input
              placeholder="Search by Patient ID"
              onChange={(e) => handleFilterChange('patientId', e.target.value)}
            />
          </Col>
          <Col xs={24} sm={12} md={6}>
            <Input
              placeholder="Search by Doctor ID"
              onChange={(e) => handleFilterChange('doctorId', e.target.value)}
            />
          </Col>
          <Col xs={24} sm={12} md={6}>
            <Select
              placeholder="Filter by State"
              style={{ width: '100%' }}
              onChange={(value) => handleFilterChange('state', value)}
            >
              <Option value="pending">Pending</Option>
              <Option value="completed">Completed</Option>
              <Option value="cancelled">Cancelled</Option>
            </Select>
          </Col>
          <Col xs={24} sm={12} md={6}>
            <RangePicker
              style={{ width: '100%' }}
              onChange={(dates) => handleFilterChange('dateRange', dates)}
            />
          </Col>
        </Row>

        <Spin spinning={loading}>
          <Table
            dataSource={consultations}
            columns={columns}
            rowKey="id"
            pagination={{ pageSize: 10 }}
            scroll={{ x: true }}
          />
        </Spin>
      </Card>

      <Modal
        title="Consultation Details"
        visible={isModalVisible}
        onCancel={() => setIsModalVisible(false)}
        footer={null}
      >
        {selectedConsultation && (
          <div>
            <p><strong>ID:</strong> {selectedConsultation.id}</p>
            <p><strong>Patient ID:</strong> {selectedConsultation.patientId}</p>
            <p><strong>Doctor ID:</strong> {selectedConsultation.DoctorId}</p>
            <p><strong>Timestamp:</strong> {moment(selectedConsultation.timeStamp).format('YYYY-MM-DD HH:mm:ss')}</p>
            <p><strong>State:</strong> <Tag color={selectedConsultation.state === 'completed' ? 'green' : 'orange'}>{selectedConsultation.state}</Tag></p>
            <p><strong>Conclusion:</strong> {selectedConsultation.conclusion}</p>
          </div>
        )}
      </Modal>
    </div>
  );
};

export default ConsultationDashboard;