CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    title VARCHAR NOT NULL,
    content VARCHAR NOT NULL
);

CREATE INDEX idx_posts_external_id ON posts (external_id);