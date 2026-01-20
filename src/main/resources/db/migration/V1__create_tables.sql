-- Create table for users
CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- Create table for schedules with constraints
CREATE TABLE tb_schedules (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'SCHEDULED',
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT ck_status CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED')),
    CONSTRAINT ck_date CHECK (end_date > start_date),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

-- Create indexes for performance optimization
CREATE INDEX idx_schedules_user_id ON tb_schedules(user_id);
CREATE INDEX idx_schedules_status ON tb_schedules(status);
CREATE INDEX idx_schedules_start_end_date ON tb_schedules(start_date, end_date);

-- Trigger to update updated_at on row modification
CREATE OR REPLACE FUNCTION update_updated_at_()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for tb_schedules
CREATE TRIGGER trg_update_schedules_updated_at
BEFORE UPDATE ON tb_schedules
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_();
