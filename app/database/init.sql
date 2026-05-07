create table servicio (
    id_servicio serial primary key,
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
    id_consulta serial primary key,
    titulo varchar(100) not null,
    estado varchar(20) default 'pendiente',
    descripcion text not null,
    fecha_creacion date default current_date,
    fecha_fin date,
    id_servicio int references servicio(id_servicio),
    id_cliente int not null,
    id_consultor int
);

create table mensajes(
    id_mensajeria serial primary key,
    id_consulta int references consulta(id_consulta),
    id_usuario int not null,
    descripcion text not null,
    asunto varchar(100) not null,
    fecha_creacion timestamp default current_timestamp
);

create or replace function fn_consulta_estado()
returns trigger as
$$
begin
    if new.estado not in ('pendiente','en progreso', 'finalizada') then
        raise exception 'El estado no es válido. Debe ser pendiente, en progreso o finalizada.';
    end if;        
end;
$$ language plpgsql;

create or replace trigger trg_consulta_estado
before update of estado on consulta
for each row
execute function fn_consulta_estado();



