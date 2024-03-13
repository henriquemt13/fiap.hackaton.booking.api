create table veiculo(
	placa char(7) primary key not null,
	tipo varchar(10),
	modelo varchar(150) not null,
	cor varchar(20) not null,
	ano char(4) not null,
	nome_dono varchar(150),
	documento_dono varchar(15),
	contato_dono varchar(18),
	created_at timestamp not null,
	created_by varchar(150),
	updated_at timestamp not null,
	updated_by varchar(150)
);

create sequence estacionamento_seq;
create table estacionamento(
	id int primary key not null DEFAULT nextval('estacionamento_seq'),
	placa char(7) not null,
	entrada timestamp not null,
	tempo_contratado varchar(15),
	saida timestamp null,
	endereco_estacionamento varchar(150),
	created_at timestamp not null,
    created_by varchar(150),
    updated_at timestamp,
    updated_by varchar(150)
);
ALTER SEQUENCE estacionamento_seq
OWNED BY estacionamento.id;

create sequence parquimetro_seq;
create table parquimetro(
	id int primary key not null DEFAULT nextval('parquimetro_seq'),
	total_vagas_cidade bigint not null,
	preco_inicial decimal (10,2),
	created_at timestamp,
    created_by varchar(150),
    updated_at timestamp,
    updated_by varchar(150)

);
ALTER SEQUENCE parquimetro_seq
OWNED BY parquimetro.id;

create sequence extrato_seq;
create table extrato(
	id int primary key not null DEFAULT nextval('extrato_seq'),
	estacionamento_id int not null,
	placa char(7) not null,
	tempo_contratado varchar(15) not null,
	valor_pago decimal(10,2) not null,
	created_at timestamp not null,
    created_by varchar(150),
    updated_at timestamp,
    updated_by varchar(150),
    constraint fk_estacionamento
            foreign key (estacionamento_id)
                references estacionamento(id)
);
ALTER SEQUENCE extrato_seq
OWNED BY extrato.id;


insert into parquimetro (total_vagas_cidade, preco_inicial) values (15000, 2.00);