class Persona {
  constructor(id, nombre, apellido, fechaNacimiento) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
  }
}

class Ciudadano extends Persona {
  constructor(id, nombre, apellido, fechaNacimiento, dni) {
    super(id, nombre, apellido, fechaNacimiento);
    this.dni = dni;
  }
}

class Extranjero extends Persona {
  constructor(id, nombre, apellido, fechaNacimiento, paisOrigen) {
    super(id, nombre, apellido, fechaNacimiento);
    this.paisOrigen = paisOrigen;
  }
}
