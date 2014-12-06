$(document).ready(function(){

    $(".buyForm").submit(function(event){
        var dish = $(this).serializeObject();
        addDishToCart(dish);
        event.preventDefault();
    });

    //add ingredient to custom dish
    $(".addIngredientForm").submit(function(event){
        var ingredient = $(this).serializeObject();
        addIngredient(ingredient);
        event.preventDefault();
    });

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

    function addIngredient(data){
        $.getJSON("/restaurace/addIngredient", data, function(){
            $("#tepIngredients").load(document.URL +  ' #tepIngredients');
        });
    }

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

    //counting various delivery prices
    $(document).ready(function(){
        var $deliveryRadio = $(".deliveryRadio:first");
        $deliveryRadio.prop("checked", true);
        if($($deliveryRadio).is(":checked")){
                    $("#deliveryPrice").text($($deliveryRadio).parent().parent().find(".deliveryPrice").text());
                    $("#totalPrice").text((parseFloat($($deliveryRadio).parent().parent().find(".deliveryPrice").text())
                        + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

    $(".deliveryRadio").click(function(){
        if($(this).is(":checked")){
            $("#deliveryPrice").text($(this).parent().parent().find(".deliveryPrice").text());
            $("#totalPrice").text((parseFloat($(this).parent().parent().find(".deliveryPrice").text())
                + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

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

});