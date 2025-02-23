import React, { useState } from 'react';
import { Layout, Menu, theme } from 'antd';
import ConsultationDashboard from './Consultation/ConsultationDashboard';
import ConsultationForm from './Consultation/ConsultationForm';

const { Sider, Content } = Layout;

const Consultation = () => {
  const [selectedKey, setSelectedKey] = useState('dashboard');
  const {
    token: { colorBgContainer },
  } = theme.useToken();

  const renderContent = () => {
    switch (selectedKey) {
      case 'dashboard':
        return <ConsultationDashboard />;
      case 'create':
        return <ConsultationForm />;
      default:
        return <ConsultationDashboard />;
    }
  };

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Sider
        width={200}
        style={{ background: colorBgContainer }}
        breakpoint="lg"
        collapsedWidth="0"
      >
        <Menu
          mode="inline"
          defaultSelectedKeys={['dashboard']}
          onClick={({ key }) => setSelectedKey(key)}
          style={{ height: '100%', borderRight: 0 }}
        >
          <Menu.Item key="dashboard">Dashboard</Menu.Item>
          <Menu.Item key="create">Create Consultation</Menu.Item>
        </Menu>
      </Sider>
      <Layout style={{ padding: '24px' }}>
        <Content
          style={{
            padding: 24,
            margin: 0,
            minHeight: 280,
            background: colorBgContainer,
          }}
        >
          {renderContent()}
        </Content>
      </Layout>
    </Layout>
  );
};

export default Consultation;