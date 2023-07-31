CREATE TABLE m_usercredential (
  id VARCHAR(36) PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255),
  role VARCHAR(36) REFERENCES m_role (id)
);