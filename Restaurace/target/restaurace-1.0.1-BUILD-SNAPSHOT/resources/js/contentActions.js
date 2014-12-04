$(document).ready(function(){

    $(".buyForm").submit(function(event){
        var dish = $(this).serializeObject();
        addDishToCart(dish);
        event.preventDefault();
    });

    $(".addIngredientForm").submit(function(event){
        var ingredient = $(this).serializeObject();
        addIngredient(ingredient);
        event.preventDefault();
    });

    function addIngredient(data){
        $.getJSON("/restaurace/addIngredient", data, function(ingredient){
            $("#tepIngredients").append("<tr><td>" + ingredient.name + ': ' + ingredient.grams + ' gramů' + "</td>"
                                          +  "<td><img src='/restaurace/resources/images/red-cross.png' /></td></tr>");
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

});