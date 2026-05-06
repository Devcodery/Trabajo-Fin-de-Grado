create table servicio (
    id serial primary key,
    nombre varchar(50) not null,
    descripcion text not null,
    categoria varchar(50) not null,
    sede varchar(50) not null,
    beneficios text not null,
    tecnologias_implicadas text not null,
    alcance text not null,
    objetivos text not null,
    fecha_creacion date default current_date,
    estado boolean not null 
);

create table consulta(
    id serial primary key,
    titulo varchar(100) not null,
    estado varchar(20) check(estado in ('pendiente', 'en proceso', 'finalizada')),
    descripcion text not null,
    fecha_creacion date default current_date,
    fecha_fin date,
    id_servicio int references servicio(id),
    id_cliente int not null references usuario(id_usuario),
    id_consultor int references usuario(id_usuario)
);

create table mensajes(
    id serial primary key,
    id_consulta int references consulta(id),
    id_usuario int not null references usuario(id_usuario),
    descripcion text not null,
    asunto varchar(100) not null,
    fecha_creacion timestamp default current_timestamp
);

