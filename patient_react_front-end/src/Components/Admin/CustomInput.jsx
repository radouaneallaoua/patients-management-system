import { Input } from "antd";

const CustomInput = ({
  value,
  onChange,
  placeholder,
  size = "large",
}) => {
  return (

      <Input
        name={placeholder}
        size={size}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        required
        
      />
  );
};

export default CustomInput;
