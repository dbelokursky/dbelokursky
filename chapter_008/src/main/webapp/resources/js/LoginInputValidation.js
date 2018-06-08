function checkForm(form) {
    // Заранее объявим необходимые переменные
    var el, // Сам элемент
        elName, // Имя элемента формы
        value, // Значение
        type; // Атрибут type для input-ов
    // Массив списка ошибок, по дефолту пустой
    var errorList = [];
    // Хэш с текстом ошибок (ключ - ID ошибки)
    var errorText = {
        1: "The login field cannot be empty. ",
        2: "The password field cannot be empty. "
    };
    // Получаем семейство всех элементов формы
    // Проходимся по ним в цикле
    for (var i = 0; i < form.elements.length; i++) {
        el = form.elements[i];
        elName = el.nodeName.toLowerCase();
        value = el.value;
        if (elName == "input") { // INPUT
            // Определяем тип input-а
            type = el.type.toLowerCase();
            // Разбираем все инпуты по типам и обрабатываем содержимое
            switch (type) {
                case "text" :
                    if (el.name == "login" && value == "") errorList.push(1);
                    break;
                case "password" :
                    if (el.name == "password" && value == "") errorList.push(2);
                    break;
                default :
                    // Сюда попадают input-ы, которые не требуют обработки
                    // type = hidden, submit, button, image
                    break;
            }
        } else {
            // Обнаружен неизвестный элемент ;)
        }
    }
    // Финальная стадия
    // Если массив ошибок пуст - возвращаем true
    if (!errorList.length) return true;
    // Если есть ошибки - формируем сообщение, выовдим alert
    // и возвращаем false
    var errorMsg = "When filling out the form, the following errors were made: \n";
    for (i = 0; i < errorList.length; i++) {
        errorMsg += errorText[errorList[i]] + "\n";
    }
    alert(errorMsg);
    return false;
}