// Login Form
var button;
var box;
var chiudiInserisciScheda;
var inserisciFormSubmit;
var chiudiCercaScheda;
var buttonRicerca;
var ricercaFormSubmit;
var boxRicerca;
var divScheda;
var chiudiSchedaBox;
var modificaSchedaButton;
var divModificaScheda;
var chiudiModificaScheda;
var erroreData;
var confermaModificaScheda;
var modificaSchedaForm;

$(function() {
    button = $('#loginButton');
    box = $('#loginBox');
    chiudiInserisciScheda = $('#chiudiInserisciScheda');
    inserisciFormSubmit = $('#inserisci');
    buttonRicerca = $('#ricercaButton');
    boxRicerca = $('#ricercaBox');
    ricercaFormSubmit = $("#ricercaFormSubmit");
    chiudiCercaScheda = $('#chiudiCercaScheda');
    divScheda = $("#schedaBox");
    chiudiSchedaBox = $("#chiudiSchedaBox");
    modificaSchedaButton = $('#modificaSchedaButton');
    divModificaScheda = $("#modificaSchedaBox");
    chiudiModificaScheda = $("#chiudiModificaSchedaBox");
    erroreData = $("#erroreData");
    confermaModificaScheda = $("#confermaModificaScheda");
    modificaSchedaForm = $("#modificaSchedaForm");
    
    button.removeAttr('href');
    button.mouseup(function(login) {
        box.toggle();
        chiudiInserisciScheda.toggle();
        button.toggleClass('active');
        buttonRicerca.removeClass('active');
        boxRicerca.hide();
        chiudiCercaScheda.hide();
        divScheda.hide();
        chiudiSchedaBox.hide();
        modificaSchedaButton.hide();
        divModificaScheda.hide();
        chiudiModificaScheda.hide();
        ricercaFormSubmit.hide();
        confermaModificaScheda.hide();
        
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
        }
    });
    
    buttonRicerca.removeAttr('href');
    buttonRicerca.mouseup(function(login) {
        boxRicerca.toggle();
        chiudiCercaScheda.toggle();
        buttonRicerca.toggleClass('active');
        ricercaFormSubmit.toggle();
        button.removeClass('active');
        box.hide();
        chiudiInserisciScheda.hide();
        inserisciFormSubmit.hide();
        divScheda.hide();
        chiudiSchedaBox.hide();
        modificaSchedaButton.hide();
        divModificaScheda.hide();
        chiudiModificaScheda.hide();
        confermaModificaScheda.hide();
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#ricercaButton').length > 0)) {
            buttonRicerca.removeClass('active');
        }
    });
});
$(function() {
    $( ".dataColloquio" ).datepicker({
        dateFormat: 'dd/mm/yy'
    });
    checkCampiRicerca();
    checkCampi();
});

$(function(){
    $("#chiudiForm").mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
            chiudiInserisciScheda.hide();
            inserisciFormSubmit.hide();
            confermaModificaScheda.hide();
        }
        if(!($(login.target).parent('#ricercaButton').length > 0)) {
            buttonRicerca.removeClass('active');
            boxRicerca.hide();
            chiudiCercaScheda.hide();
            confermaModificaScheda.hide();
        }
    });
});
$(function(){
    $("#chiudiForm2").mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
            chiudiInserisciScheda.hide();
        }
        if(!($(login.target).parent('#ricercaButton').length > 0)) {
            buttonRicerca.removeClass('active');
            boxRicerca.hide();
            chiudiCercaScheda.hide();
        }
    });
});
$(function(){
    $("#chiudiRicerca").mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
            chiudiInserisciScheda.hide();
            ricercaFormSubmit.hide();
        }
        if(!($(login.target).parent('#ricercaButton').length > 0)) {
            buttonRicerca.removeClass('active');
            boxRicerca.hide();
            chiudiCercaScheda.hide();
            ricercaFormSubmit.hide();
        }
    });
});
$(function(){
    $("#chiudiSchedaBox").mouseup(function(login) {
        divScheda.hide();
        chiudiSchedaBox.hide();
        modificaSchedaButton.hide();
    });
});


$(function() {
    $("button").button();
    $("input:submit").button();
    
});

//var name = "#left";  
//var menuYloc = null;  
//      
//$(document).ready(function(){  
//    menuYloc = parseInt($(name).css("top").substring(0,$(name).css("top").indexOf("px")))  
//    $(window).scroll(function () {  
//        var offset = menuYloc+$(document).scrollTop()+"px";  
//        $(name).animate({
//            top:offset
//        },{
//            duration:200,
//            queue:false
//        });  
//    });  
//});

            
            
function checkCampi(){
    nomeCandidato = $("#nomeCandidato").val();
    cognomeCandidato = $("#cognomeCandidato").val();
    dataColloquio = $("#dataColloquio").val();
    if((nomeCandidato != "") && (cognomeCandidato != "") && (dataColloquio != "")){
        $('#inserisci').fadeIn(100);
    }else
        $('#inserisci').fadeOut(100);
}

function checkCampiRicerca(){
    nomeCandidato = $("#nomeCandidatoRicerca").val();
    cognomeCandidato = $("#cognomeCandidatoRicerca").val();
    dataColloquio = $("#dataColloquioRicerca").val();
    dataColloquioInizio = $("#dataInizioRicerca").val();
    dataColloquioFine = $("#dataFineRicerca").val();
    if((nomeCandidato == "") && (cognomeCandidato == "") && (dataColloquio == "") 
        && (dataColloquioInizio == "") && (dataColloquioFine == "")){
        $('#inserisciRicerca').hide();
    }else
        $('#inserisciRicerca').fadeIn(100);
}
            
function checkDate(input){
    var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
    var returnval=false
    if (!validformat.test(input.value)){
        
        $("#erroreData").toggle();
        $("#modifica").hide();
    }
    else{ //Detailed check for valid date ranges
        var monthfield=input.value.split("/")[1]
        var dayfield=input.value.split("/")[0]
        var yearfield=input.value.split("/")[2]
        var dayobj = new Date(yearfield, monthfield-1, dayfield)
        if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
        {
            
            $("#erroreData").toggle();
            $("#modifica").hide();
        }
        else{
            
            returnval=true;
            $("#erroreData").hide();
            $("#modifica").toggle();
            
        }
            
    }
    if (returnval==false) input.select()
    return returnval
}  


function visualizzaScheda(idScheda){
    
    $("#scheda-visualizzate").remove();
    $("#modificaSchedaButton").hide();
    $("#modificaSchedaForm").remove();
    
    divScheda = $("#schedaBox");
    chiudiSchedaBox = $("#chiudiSchedaBox");
    divModificaScheda = $("#modificaSchedaBox");
    //    chiudiModificaScheda = $("#chiudiModificaScheda");
    button = $('#loginButton');
    box = $('#loginBox');
    chiudiInserisciScheda = $('#chiudiInserisciScheda');
    inserisciFormSubmit = $('#inserisci');
    buttonRicerca = $('#ricercaButton');
    ricercaFormSubmit = $("#ricercaFormSubmit");
    boxRicerca = $('#ricercaBox');
    chiudiCercaScheda = $('#chiudiCercaScheda');
    confermaModificaScheda = $("#confermaModificaScheda");
    modificaSchedaForm = $("#modificaSchedaForm");
    
    divScheda.toggle();
    chiudiSchedaBox.toggle();
    buttonRicerca.removeClass('active');
    boxRicerca.hide();
    chiudiCercaScheda.hide();
    button.removeClass('active');
    box.hide();
    chiudiInserisciScheda.hide();
    inserisciFormSubmit.hide();
    erroreData.hide();
    ricercaFormSubmit.hide();
    confermaModificaScheda.hide();
    
    
    var tabellaValutazioni = "";
    var tipoValutazione = "";
    $.getJSON('/EasyFeedback/simple/json/visualizza-scheda-json.action?idScheda=' + idScheda, function(info) {
        
        for (i = 0; i < info.scheda.valutazioni.length; i++) {
            tabellaValutazioni+=   
            "<tr>"+
            "<td><b>" + info.scheda.valutazioni[i].voce.voce+ "</b></td>"+
            "<td>" + info.scheda.valutazioni[i].giudizio + "</td>"+
            "</tr>";
        }
        if(info.profiloUser == 'ADMIN'){
            $('#modificaSchedaButton').toggle();
            
            var tabella = $('<table id="scheda-visualizzate">'+
                '<tr><td><b>ID Scheda</b></td><td>' + info.scheda.id + '</td></tr>' +
                '<tr><td><b>Nome candidato</b></td><td>' + info.scheda.nomeCandidato + '</td></tr>' +
                '<tr><td><b>Cognome candidato</b></td><td>' + info.scheda.cognomeCandidato + '</td></tr>' +
                '<tr><td><b>Data colloquio</b></td><td>' + info.data + '</td></tr>' +
                '<tr><td><b>Valutazione Complessiva</b></td><td>' + info.scheda.valutazioneComplessiva + '</td></tr>' +
                '<tr><td><b>Download excel</b></td><td>' + info.scheda.urlActionExcel + '</td></tr>' +
                '<tr>'+
                '<td><b>Voce</b></td>'+
                '<td><b>Giudizio</b></td>'+
                '</tr>' +
                tabellaValutazioni
                +'</table>');
        }
        else{
            var tabella = $('<table id="scheda-visualizzate">'+
                '<tr><td>ID Scheda</td><td>' + info.scheda.id + '</td></tr>' +
                '<tr><td>Nome candidato</td><td>' + info.scheda.nomeCandidato + '</td></tr>' +
                '<tr><td>Cognome candidato</td><td>' + info.scheda.cognomeCandidato + '</td></tr>' +
                '<tr><td>Data colloquio</td><td>' + info.data + '</td></tr>' +
                '<tr><td>Valutazione Complessiva</td><td>' + info.scheda.valutazioneComplessiva + '</td></tr>' +
                '<tr><td>Download excel</td><td>' + info.scheda.urlActionExcel + '</td></tr>' +
                '<tr>'+
                '<td>Voce</td>'+
                '<td>Giudizio</td>'+
                '</tr>' +
                tabellaValutazioni
                +'</table>');   
        }
        divScheda.append(tabella);
        
        
        
        
        for (x = 0; x < info.scheda.valutazioni.length; x++) {
            if(info.scheda.valutazioni[x].voce.tipoValutazione == "GIUDIZIO"){
                
                tipoValutazione += '<tr><td>'+info.scheda.valutazioni[x].voce.voce+": </td><td>" + "<select name='valutazione' value='"+ info.scheda.valutazioni[x].giudizio +"'>";
                for(y=0; y<info.giudiziLetterali.length; y++){
                    if(info.giudiziLetterali[y] == info.scheda.valutazioni[x].giudizio)
                        tipoValutazione += "<option selected='selected' value='" + info.giudiziLetterali[y] + "'>" + info.giudiziLetterali[y] + "</option>";
                    else
                        tipoValutazione += "<option value='" + info.giudiziLetterali[y] + "'>" + info.giudiziLetterali[y] + "</option>";
                }
                tipoValutazione += "</select></td></tr>";
            }
            if(info.scheda.valutazioni[x].voce.tipoValutazione == "TEST"){
                tipoValutazione += '<tr><td>'+info.scheda.valutazioni[x].voce.voce+": </td><td>" + "<select name='valutazione' value='"+ info.scheda.valutazioni[x].giudizio +"'>";
                for(y=0; y<info.giudiziNumerici.length; y++){
                    if(info.giudiziNumerici[y] == info.scheda.valutazioni[x].giudizio)
                        tipoValutazione += "<option selected='selected' value='" + info.giudiziNumerici[y] + "'>" + info.giudiziNumerici[y] + "</option>";
                    else
                        tipoValutazione += "<option value='" + info.giudiziNumerici[y] + "'>" + info.giudiziNumerici[y] + "</option>";
                }
                tipoValutazione += "</select></td></tr>";
            }
            if(info.scheda.valutazioni[x].voce.tipoValutazione == "ESPERIENZA"){
                tipoValutazione += '<tr><td>'+info.scheda.valutazioni[x].voce.voce + ": </td><td>" + "<input type='text' name='valutazione' value='" + info.scheda.valutazioni[x].giudizio + "'/></td></tr>";
            }
        }
        
        var modificaSchedaForm = $('<form id="modificaSchedaForm" action="/EasyFeedback/admin/modifica-scheda.action" method="post">'+
            "<table><tr><td>"+
            "Nome candidato: </td><td>"+
            '<input type="text" name="scheda.nomeCandidato" id="nomeCandidatoModifica" value="' + info.scheda.nomeCandidato + '" onkeyup="checkCampiModifica()"/></td></tr>'+'<tr><td>Cognome candidato: </td><td>'+
            '<input type="text" name="scheda.cognomeCandidato" id="cognomeCandidatoModifica"  onkeyup="checkCampiModifica()" value="' + info.scheda.cognomeCandidato + '"/></td></tr>'+'<tr><td>Data colloquio: </td><td>'+
            '<input type="text" name="scheda.dataColloquio" onchange="checkDate(this)" class="dataColloquio" id="dataColloquioModifica" onchange="checkCampiModifica()" accesskey="false" value="' + info.data + '"/></td></tr>'+
            tipoValutazione +"<tr><td>Valutazione Complessiva: </td><td>"+
            '<input type="textarea" name="scheda.valutazioneComplessiva" id="valutazioneComplessivaModifica" value="' + info.scheda.valutazioneComplessiva + '"/></td></tr>'+
            '<tr><td></td><td><div id="erroreData">gg/mm/aaaa</div</td></tr></table>'+
            '</form>');
        
        divModificaScheda.append(modificaSchedaForm);
        
    //        divModificaScheda.append("<h1>OK</h1>");
    })
}

$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
		$( "#messaggio-scheda-salvata" ).dialog({
			modal: true,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	});
