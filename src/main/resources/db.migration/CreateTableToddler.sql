CREATE TABLE m_toddler (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(255),
  age INTEGER,
  phone VARCHAR(255) UNIQUE,
  address VARCHAR(255),
  weight VARCHAR(255),
  height VARCHAR(255),
  headCircumference VARCHAR(255),
  counseling BOOLEAN,
  immunization BOOLEAN,
  staff_id VARCHAR(36) REFERENCES m_staff (id),
  status BOOLEAN NOT NULL,
  create_at TIMESTAMP NOT NULL,
  update_at TIMESTAMP NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  updated_by VARCHAR(255) NOT NULL
);