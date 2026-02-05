CREATE TABLE regionais (
    surrogate_key SERIAL PRIMARY KEY,
    id INTEGER NOT NULL,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN NOT NULL
);