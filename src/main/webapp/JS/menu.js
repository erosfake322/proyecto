$(document).ready(function () {
    console.log('JQuery esta funcionando');

    function Listar() {
        $.post('CSede', {op: "listar"}, function (response) {
//            console.log(response);
            const datos = JSON.parse(response);
            console.log(datos);
            let template = '';
            datos.forEach(item => {
                template += `
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.nombre}</td>
                                <td>${item.logico}</td>
                                <td>
                                    <button class="btn btn-warning btn-sm editar" id="${item.id}">
                                        E
                                    </button>
                                    <button class="btn btn-danger btn-sm eliminar" id="${item.id}">
                                        D
                                    </button>
                                </td>
                            </tr>
                            `;
            });

            $('#tbody-list').html(template);

        });
    }
    Listar();

    function Obtener() {
        let id = $('#id').val();
        let nombre = $('#nombre').val();
        let logico = false;
        if ($('#checkLogico').prop('checked')) {
            logico = true;
        }

        let op;
        if (id.length === 0) {
            op = 'insertar';
        } else {
            op = 'actualizar';
        }
        const datos = {op, id, nombre, logico};
        return datos;
    }

    function Limpiar() {
        $('#id').val('');
        $('#nombre').val('');
        $('#checkLogico').prop('checked', false);
        $('#respLogico').html('NO');
    }

    $('#registrar').click(function () {
        console.log('ENTRO AL BOTON');
        const datos = Obtener();
        console.log(datos);

        $.post('CSede', datos, function (respuesta) {
            console.log('CLICK REGISTRA  ' + respuesta);
            if (respuesta === "EXITOI") {
                ListarTabla();
            } else if (respuesta === "EXITOU") {
                ListarTabla();
            } else {
                alert("ERROR");
            }
            Limpiar();
        });
    });

//    $('#registrar').click(function () {
//        const datos = Obtener();
//        console.log(datos);
//        $.post('CSede', datos, function (response) {
//            console.log(response);
//            Listar();
//            Limpiar();
//        });
//    });

    $(document).on('click', '.editar', function () {
        const id = $(this).attr('id');
        $.post('CSede', {op: "listarID", id}, function (response) {
            console.log(response);
            const datos = JSON.parse(response);

            $('#id').val(datos[0].id);
            $('#nombre').val(datos[0].nombre);
            $('#checkLogico').prop('checked', datos[0].logico);
            if (datos[0].logico) {
                $('#respLogico').html('SI');
            }
        });
    });

    $(document).on('click', '.eliminar', function () {
        const id = $(this).attr('id');
        $.post('CSede', {op: "eliminar", id}, function (response) {
            console.log(response);
            Listar();
        });
    });



//CHECKBOX LOGICO 
    $('#checkLogico').change(function () {
        let respLogico = $('#respLogico');
        if (this.checked) {
            // console.log('SI');
            respLogico.html('SI');
        } else {
            // console.log('NO');
            respLogico.html('NO');
        }
    });
});