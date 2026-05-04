create table servicios (
    id serial primary key,
    nombre varchar(50) not null,
    descripcion text not null,
    categoria varchar(50) not null,
    sede varchar(50) not null,
    fecha_creacion timestamp, 
    fecha_fim date,
    estado boolean not null 
);

create table solicitudes(
    id serial primary key,
    titulo varchar(100) not null,
    estado varchar(20) check(estado in ('pendiente', 'en_proceso', 'completada')) not null,
    descripcion text not null,
    fecha_creacion timestamp,
    idServicio int references servicios(id)
);

create table usuarios(
    id int primary key,
    nombre varchar(100) not null,
    email varchar(150) not null unique,
    password varchar(255) not null
);

create table clientes(
    id references usuarios(id) primary key
);
create table consultores(
    id references usuarios(id) primary key
);

create table Mensajes(
    id serial primary key,
    idSolicitud int references solicitudes(id),
    idUsuario int references usuarios(id),
    descripcion text not null,
    asunto varchar(100) not null,
    fecha_creacion timestamp
);