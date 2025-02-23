import { useParams } from "react-router-dom";
import CustomInput from "./CustomInput";
import CustomButton from "./CustomButton";
import { useState } from "react";
import { Button } from "antd";
import { UploadOutlined } from "@ant-design/icons";

const EditCategory = () => {
  const params = useParams();
  const [categoryInfo, setCategoryInfo] = useState({
    name: "",
    description: "",
    file: null,
  });

  const onChange = (event) => {
    const { name, value } = event.target;
    if (name === "file") {
      setCategoryInfo((pre) => ({ ...pre, [name]: event.target.files[0] }));
    }
    setCategoryInfo((pre) => ({ ...pre, [name]: value }));
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      categoryInfo.name.length > 0 &&
      categoryInfo.description.length > 0 &&
      categoryInfo.file
    ) {
      alert(JSON.stringify(categoryInfo));
    }
  };

  const handleFileUpload = () => {
    document.getElementById("fileUpload").click();
    setCategoryInfo((pre) => ({ ...pre, file: null }));
  };
  return (
    <div>
      EditCategory {params["id"]}
      <form onSubmit={handleSubmit}>
        <div className="row ">
          <div className="col-md-5 my-3">
            <CustomInput
              label="name"
              placeholder="name"
              onChange={onChange}
              value={categoryInfo.name}
            />
          </div>
          <div className="col-md-5  my-3">
            <CustomInput
              label="description"
              placeholder="description"
              onChange={onChange}
              value={categoryInfo.description}
            />
          </div>
        </div>
        <div className="row ">
          <div className="col-md-8">
            <CustomButton
              onClick={handleFileUpload}
              type="default"
              text="choose category's image"
              icon={<UploadOutlined />}
            />
            {categoryInfo.file == null ? (
              <span className="p-2 mx-2 text-white rounded-pill bg-danger">
                no file choosen
              </span>
            ) : (
              <span className="p-2 mx-2 text-white  rounded-pill bg-success">
                {categoryInfo.file}
              </span>
            )}
            <input
              type="file"
              id="fileUpload"
              hidden
              value={categoryInfo.file}
              name="file"
              onChange={onChange}
            />
          </div>
        </div>
        <div className="col-md-2 my-3">
          <CustomButton
            actionType="submit"
            onClick={null}
            text="Save changes"
          />
          {categoryInfo.file?.name}
        </div>
      </form>
    </div>
  );
};

export default EditCategory;
