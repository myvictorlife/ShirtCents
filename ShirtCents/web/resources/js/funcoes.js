function verifica() {
    if(window.confirm("Tem certeza?")) {
        return true;
    }
    return false;
}

//Login
    function handleLoginRequest(xhr, status, args) {
        if(args.validationFailed || !args.loggedIn) {
            PF('dlg').jq.effect("shake", {times:5}, 100);
        }
        else {
            PF('dlg').hide();
            $('#loginLink').fadeOut();
        }
    }
