CREATE TABLE m_elderly (
       id VARCHAR(36) PRIMARY KEY,
       name VARCHAR(255),
       age INTEGER,
       phone VARCHAR(255) UNIQUE,
       address VARCHAR(255),
       weight VARCHAR(255),
       height VARCHAR(255),
       bloodPressure VARCHAR(255),
       counseling BOOLEAN,
       healthServices BOOLEAN,
       staff_id VARCHAR(36) REFERENCES m_staff (id),
       status BOOLEAN NOT NULL,
       create_at TIMESTAMP NOT NULL,
       update_at TIMESTAMP NOT NULL,
       created_by VARCHAR(255) NOT NULL,
       updated_by VARCHAR(255) NOT NULL
);