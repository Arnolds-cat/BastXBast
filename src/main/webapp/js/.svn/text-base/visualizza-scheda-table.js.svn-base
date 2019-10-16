//$(function(){
//    $( "#modificaSchedaBox" ).hide();
//               
//});
function toggleModificaSchedaBox(){
    $("#schedaBox").hide();
    $("#chiudiSchedaBox").hide();
    $("#modificaSchedaBox").toggle();
    $("#erroreData").hide();
    $("#chiudiModificaSchedaBox").toggle();
    $('#modificaSchedaButton').hide();
    $("#confermaModificaScheda").toggle();
    
}

$(function(){
    $("#chiudiModificaScheda").mouseup(function(login) {
        $("#modificaSchedaBox").hide();
        $("#chiudiModificaSchedaBox").hide();
        $("#confermaModificaScheda").hide();
    });
});

function checkCampiModifica(){
    nomeCandidato = $("#nomeCandidatoModifica").val();
    cognomeCandidato = $("#cognomeCandidatoModifica").val();
    dataColloquio = $("#dataColloquioModifica").val();
//    if((nomeCandidato != "") && (cognomeCandidato != "") && (dataColloquio != "")){
//        $('#inserisci').fadeIn(100);
//    }else
//        $('#inserisci').fadeOut(100);
}
$(function() {
    $( ".dataColloquio" ).datepicker({
        dateFormat: 'dd/mm/yy'
    });
    checkCampiModifica();
});