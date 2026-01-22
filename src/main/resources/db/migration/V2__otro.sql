CREATE TABLE public.alumno (
    id bigserial PRIMARY KEY,
    nombre text NOT NULL,
    email text,
    activo boolean DEFAULT true
);