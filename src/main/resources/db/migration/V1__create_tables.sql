CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    capa_url VARCHAR(500)
);

CREATE TABLE artista_album (
    artista_id INT NOT NULL,
    album_id INT NOT NULL,
    PRIMARY KEY (artista_id, album_id),
    CONSTRAINT fk_artista FOREIGN KEY (artista_id) REFERENCES artista(id),
    CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES album(id)
);

CREATE TABLE regional (
    id INT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Carga inicial para você testar depois
INSERT INTO artista (nome, tipo) VALUES ('Serj Tankian', 'CANTOR');
INSERT INTO artista (nome, tipo) VALUES ('Guns N’ Roses', 'BANDA');