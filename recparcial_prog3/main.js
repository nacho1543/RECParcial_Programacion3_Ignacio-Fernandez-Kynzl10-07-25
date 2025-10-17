 const URL = "https://examenesutn.vercel.app/api/PersonaCiudadanoExtranjero";
let lista = [];

const tablaBody = document.querySelector("#tabla-personas tbody");
const formLista = document.getElementById("form-lista");
const formABM = document.getElementById("form-abm");
const spinner = document.getElementById("spinner");

const form = document.getElementById("formulario");
const titulo = document.getElementById("form-titulo");
const btnAgregar = document.getElementById("btn-agregar");
const btnCancelar = document.getElementById("btn-cancelar");

const id = document.getElementById("id");
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const fechaNacimiento = document.getElementById("fechaNacimiento");
const dni = document.getElementById("dni");
const paisOrigen = document.getElementById("paisOrigen");
const tipo = document.getElementById("tipo");

const campoDni = document.getElementById("campo-dni");
const campoPais = document.getElementById("campo-pais");

let modo = "alta";
let elementoActual = null;

function mostrarSpinner() {
  spinner.classList.remove("oculto");
}

function ocultarSpinner() {
  spinner.classList.add("oculto");
}

function mostrarFormularioLista() {
  formLista.classList.remove("oculto");
  formABM.classList.add("oculto");
  renderizarTabla();
}

function mostrarFormularioABM(modoActual, persona = null) {
  modo = modoActual;
  elementoActual = persona;
  titulo.textContent = modo.charAt(0).toUpperCase() + modo.slice(1);

  formLista.classList.add("oculto");
  formABM.classList.remove("oculto");

  id.value = persona?.id || "";
  nombre.value = persona?.nombre || "";
  apellido.value = persona?.apellido || "";
  fechaNacimiento.value = persona?.fechaNacimiento || "";

  if (persona instanceof Ciudadano || tipo.value === "Ciudadano") {
    campoDni.classList.remove("oculto");
    campoPais.classList.add("oculto");
    dni.value = persona?.dni || "";
  } else {
    campoDni.classList.add("oculto");
    campoPais.classList.remove("oculto");
    paisOrigen.value = persona?.paisOrigen || "";
  }

  tipo.disabled = modo !== "alta";
  id.disabled = true;
}

function renderizarTabla() {
  tablaBody.innerHTML = "";
  lista.forEach(p => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${p.id}</td>
      <td>${p.nombre}</td>
      <td>${p.apellido}</td>
      <td>${p.fechaNacimiento}</td>
      <td>${p instanceof Ciudadano ? p.dni : "N/A"}</td>
      <td>${p instanceof Extranjero ? p.paisOrigen : "N/A"}</td>
      <td><button onclick="modificar(${p.id})">Modificar</button></td>
      <td><button onclick="eliminar(${p.id})">Eliminar</button></td>
    `;
    tablaBody.appendChild(tr);
  });
}

function modificar(idBuscado) {
  const persona = lista.find(p => p.id === idBuscado);
  mostrarFormularioABM("modificación", persona);
}

function eliminar(idBuscado) {
  const persona = lista.find(p => p.id === idBuscado);
  mostrarFormularioABM("baja", persona);
}

btnAgregar.onclick = () => mostrarFormularioABM("alta");
btnCancelar.onclick = mostrarFormularioLista;

form.onsubmit = async (e) => {
  e.preventDefault();

  const datos = {
    nombre: nombre.value,
    apellido: apellido.value,
    fechaNacimiento: parseInt(fechaNacimiento.value),
  };

  if (tipo.value === "Ciudadano") {
    if (modo !== "baja" && dni.value <= 0) return alert("DNI debe ser mayor a 0");
    datos.dni = parseInt(dni.value);
  } else {

    if (modo !== "baja" && paisOrigen.value.trim() === "") return alert("País de origen es obligatorio");
    datos.paisOrigen = paisOrigen.value.trim();
  }

  mostrarSpinner();

  try {
    let respuesta;
    if (modo === "alta") {
      respuesta = await fetch(URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datos),
      });

      if (respuesta.ok) {
        const nuevo = await respuesta.json();
        const persona = tipo.value === "Ciudadano"
          ? new Ciudadano(nuevo.id, datos.nombre, datos.apellido, datos.fechaNacimiento, datos.dni)
          : new Extranjero(nuevo.id, datos.nombre, datos.apellido, datos.fechaNacimiento, datos.paisOrigen);

        lista.push(persona);
      } else {
        alert("No se pudo realizar el alta.");
      }

    } else if (modo === "modificación") {
      datos.id = elementoActual.id;
      respuesta = await fetch(URL, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datos),
      });

      if (respuesta.ok) {
        const index = lista.findIndex(p => p.id === datos.id);
        lista[index] = tipo.value === "Ciudadano"
          ? new Ciudadano(datos.id, datos.nombre, datos.apellido, datos.fechaNacimiento, datos.dni)
          : new Extranjero(datos.id, datos.nombre, datos.apellido, datos.fechaNacimiento, datos.paisOrigen);
      } else {
        alert("No se pudo realizar la modificación.");
      }

    } else if (modo === "baja") {
      respuesta = await fetch(URL, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id: elementoActual.id }),
      });

      if (respuesta.ok) {
        lista = lista.filter(p => p.id !== elementoActual.id);
      } else {
        alert("No se pudo realizar la eliminación.");
      }
    }

  } catch (error) {
    alert("Error en la operación.");
  }

  ocultarSpinner();
  mostrarFormularioLista();
};

(async function cargarInicial() {
  mostrarSpinner();
  try {
    const res = await fetch(URL);
    if (!res.ok) throw new Error("Error al obtener datos");

    const data = await res.json();
    lista = data.map(obj => {
      if ("dni" in obj) return new Ciudadano(obj.id, obj.nombre, obj.apellido, obj.fechaNacimiento, obj.dni);
      if ("paisOrigen" in obj) return new Extranjero(obj.id, obj.nombre, obj.apellido, obj.fechaNacimiento, obj.paisOrigen);
      return new Persona(obj.id, obj.nombre, obj.apellido, obj.fechaNacimiento);
    });

    mostrarFormularioLista();
  } catch (error) {
    alert("No se pudo cargar la lista inicial.");
  }
  ocultarSpinner();
})();
