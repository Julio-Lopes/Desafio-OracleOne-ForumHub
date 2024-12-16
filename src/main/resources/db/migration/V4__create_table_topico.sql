CREATE TABLE Topico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    FOREIGN KEY (autor) REFERENCES Usuario(id),
    FOREIGN KEY (curso) REFERENCES Curso(id)
);