CREATE TABLE m_admin (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  user_credential_id VARCHAR(36) REFERENCES user_credential (id),
  create_at TIMESTAMP NOT NULL,
  CONSTRAINT fk_user_credential FOREIGN KEY (user_credential_id) REFERENCES user_credential (id)
);