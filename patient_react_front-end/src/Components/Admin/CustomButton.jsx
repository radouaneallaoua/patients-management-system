import { Button } from "antd";

const CustomButton = ({
  type = "primary",
  actionType = "button",
  onClick,
  text,
  shape = "default",
  icon=null
}) => {
  return (
    <Button
    
      shape={shape}
      size="large"
      htmlType={actionType}
      type={type}
      onClick={onClick}
      icon={icon}
    >
      {text}
    </Button>
  );
};

export default CustomButton;
