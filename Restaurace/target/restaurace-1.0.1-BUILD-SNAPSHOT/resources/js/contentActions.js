$(document).ready(function(){

    /*
     * add dish to cart on form submit
     */
    $(".buyForm").submit(function(event){
        var dish = $(this).serializeObject();
        addDishToCart(dish);
        event.preventDefault();
    });

    /*
     * add ingredient to custom dish on form submit
     */
    $(".addIngredientForm").submit(function(event){
        var ingredient = $(this).serializeObject();
        addIngredient(ingredient);
        event.preventDefault();
    });

    /*
     * on submit event remove dish from cart, related to the whole document
     * in order to prevent issues after ajax reloads of page fragments
     */
    $(document).on('submit', ".removeCartItem", function(event){
        var id = $(this).serializeObject();
        removeCartDishItem(id);
        event.preventDefault();
    });


    //TODO dodelat rucni aktualizaci kosiku v headeru
    /*
     * calls controller method with mapping "/restaurace/removeItem",
     * gets output, if valid JSON, executes function, which reaload some page fragments
     */
    function removeCartDishItem(data){
        $.getJSON("/restaurace/removeItem", data, function(){
            $("#cartItems").load(document.URL + ' #cartItems');
            $("#cartProperties").load(document.URL + ' #cartProperties');
        })
    }

    /*
     * calls controller method with mapping "/restaurace/addIngredient",
     * gets output, if valid JSON, executes function, which reaload some page fragments
     */
    function addIngredient(data){
        $.getJSON("/restaurace/addIngredient", data, function(){
            $("#tepIngredients").load(document.URL +  ' #tepIngredients');
        });
    }

    /*
     * calls controller method with mapping "/restaurace/addToCart",
     * gets output, if valid JSON, executes function, which recounts some fields on a page
     */
    function addDishToCart(dish){
        $.getJSON("/restaurace/addToCart", dish, function(){
            var currentAmount = parseInt($("#totalItems").text());
            var additionalAmount = parseInt(dish.amount);

            var currentPrice = parseFloat($("#cartPrice").text());
            var additionalPrice = (parseFloat(dish.price) * parseFloat(additionalAmount));

            $("#totalItems").text(currentAmount + additionalAmount);
            $("#cartPrice").text((currentPrice + additionalPrice).toFixed(2));
        });
    }

    /*
     * counting various delivery prices on page LOAD
     */
    $(document).ready(function(){
        var $deliveryRadio = $(".deliveryRadio:first");
        $deliveryRadio.prop("checked", true);
        if($($deliveryRadio).is(":checked")){
                    $("#deliveryPrice").text($($deliveryRadio).parent().parent().find(".deliveryPrice").text());
                    $("#totalPrice").text((parseFloat($($deliveryRadio).parent().parent().find(".deliveryPrice").text())
                        + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

    /*
     * counting various delivery prices on radio button click
     */
    $(".deliveryRadio").click(function(){
        if($(this).is(":checked")){
            $("#deliveryPrice").text($(this).parent().parent().find(".deliveryPrice").text());
            $("#totalPrice").text((parseFloat($(this).parent().parent().find(".deliveryPrice").text())
                + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

    /*
     * not used yet
     */
    function getUrlParameter(sParam)
    {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++)
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam)
            {
                return sParameterName[1];
            }
        }
    }

    //remove ingredient from custom dish
    /*$("#actualIngredients .remove").click(function(){
        var id = this.name;
        removeIngredient(id);
    });

    function removeIngredient(data){
        $.getJSON("/restaurace/removeIngredient",{id: data}, function(){
            $("#tepIngredients").load(document.URL +  ' #tepIngredients');
        });
    }*/
});