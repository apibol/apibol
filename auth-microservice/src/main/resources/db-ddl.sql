-- Client Details
CREATE DATABASE auth;

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

-- Client Token
CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

-- Access token
CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

-- Refresh token
CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

-- OAuth Code
CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

-- OAuth Approvals
CREATE TABLE IF NOT EXISTS oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

-- Scope
CREATE TABLE IF NOT EXISTS scope (
  id VARCHAR(100),
  scope VARCHAR(255)
);

-- Credential
CREATE TABLE IF NOT EXISTS credential (
  id VARCHAR(255),
  email VARCHAR(500),
  nickname VARCHAR(100),
  password VARCHAR(500)
);

-- Credential X Scope
CREATE TABLE IF NOT EXISTS credentials_scope (
  id VARCHAR(255),
  nickname VARCHAR(100),
  scope VARCHAR(50)
);



