import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Card, Spin, Descriptions } from 'antd';
import axios from 'axios';
import DiagnosticForm from './DiagnosticForm';

const DiagnosticPage = () => {
  const { id } = useParams();
  const [diagnostic, setDiagnostic] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchDiagnostic = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/diagnostics/${id}`);
        setDiagnostic(response.data);
      } catch (error) {
        console.error('Failed to fetch diagnostic:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchDiagnostic();
  }, [id]);

  return (
    <Card title="Diagnostic Details" style={{ margin: '24px' }}>
      <Spin spinning={loading}>
        {diagnostic && (
          <Descriptions bordered column={1}>
            <Descriptions.Item label="ID">{diagnostic.id}</Descriptions.Item>
            <Descriptions.Item label="Symptoms">{diagnostic.symptoms}</Descriptions.Item>
            <Descriptions.Item label="Condition">{diagnostic.condition}</Descriptions.Item>
            <Descriptions.Item label="Date">{diagnostic.date}</Descriptions.Item>
            <Descriptions.Item label="Notes">{diagnostic.notes}</Descriptions.Item>
          </Descriptions>
        )}
        <DiagnosticForm></DiagnosticForm>
      </Spin>
    </Card>
  );
};

export default DiagnosticPage;