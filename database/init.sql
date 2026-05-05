create table servicios (
    id serial primary key,
    nombre varchar(50) not null,
    descripcion text not null,
    categoria varchar(50) not null,
    sede varchar(50) not null,
    beneficios text not null,
    tecnologias_aplicadas text not null,
    alcance text not null,
    objetivo text not null,
    fecha_creacion timestamp, 
    estado boolean not null 
);

create table solicitudes(
    id serial primary key,
    titulo varchar(100) not null,
    estado varchar(20) check(estado in ('pendiente', 'en proceso', 'completada')) not null,
    descripcion text not null,
    fecha_creacion timestamp,
    fecha_fim date,
    idServicio int references servicios(id)
);

create table usuarios(
    id int primary key,
    nombre varchar(100) not null,
    email varchar(150) not null unique,
    password varchar(255) not null,
    rol varchar(20) check(rol in ('cliente', 'consultor')) not null
);

create table clientes(
    idCliente int references usuarios(id),
    primary key(idCliente)
);
create table consultores(
    idConsultor int references usuarios(id),
    primary key(idConsultor)
);

create table Mensajes(
    id serial primary key,
    idSolicitud int references solicitudes(id),
    idUsuario int references usuarios(id),
    descripcion text not null,
    asunto varchar(100) not null,
    fecha_creacion timestamp
);

