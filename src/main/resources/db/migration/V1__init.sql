CREATE TABLE public.alumno (
    id bigserial PRIMARY KEY,
    nombre text NOT NULL,
    email text,
    edad integer,
    curso text,
    activo boolean DEFAULT true
);