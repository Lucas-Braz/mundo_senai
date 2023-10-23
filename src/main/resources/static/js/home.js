$("#salvar").click(sendForm);

function sendForm(){
    let nome = $("#nome").val();
    let data = $("#data").val();
    let email = $("#email").val();
    let telefone = $("#telefone").val();

    $.ajax({
        type: "POST",
        url: "/",
        data:{
            nome:nome,
            data_nasc:data,
            email:email,
            telefone:telefone,
        },
        success: function(data){
            alertaRequest(data.mensagem, data.sucesso);
        },
        error: function (){
            alert("Falha na comunicação com o servidor.")
        }
    });
}

function alertaRequest(mensagem,sucesso){
    Swal.fire({
        position: 'top-end',
        toast: true,
        icon: sucesso ? 'success' : 'error',
        title: mensagem,
        showConfirmButton: false,
        timer: 2000
    });
}