CREATE TABLE m_staff (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(255) UNIQUE NOT NULL,
  user_credential_id VARCHAR(36) REFERENCES user_credential (id),
  status BOOLEAN NOT NULL,
  create_at TIMESTAMP NOT NULL,
  update_at TIMESTAMP NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  updated_by VARCHAR(255) NOT NULL
);