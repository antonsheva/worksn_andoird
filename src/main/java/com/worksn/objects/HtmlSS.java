package com.worksn.objects;

public class HtmlSS {
    public String strDecode(String txt){
        txt = txt.replace("<br>", "\n");
        txt = txt.replace("<b>","");
        txt = txt.replace("</b>","");

        txt = txt.replace("&lt;br&gt;","\n");
        txt = txt.replace("&lt;b&gt;","");
        txt = txt.replace("&lt;/b&gt;","");

        txt = txt.replace("&nbsp;"  ,"");//			неразрывныйпробел
        txt = txt.replace("&pound;" ,"£");//			фунтстерлингов
        txt = txt.replace("&euro;"  ,"€");//			знакевро
        txt = txt.replace("&para;"  ,"¶");//			символпараграфа
        txt = txt.replace("&sect;"  ,"§");//			параграф
        txt = txt.replace("&copy;"  ,"©");//			знакcopyright
        txt = txt.replace("&reg;"   ,"®");//			знакзарегистрированнойторговоймарки
        txt = txt.replace("&trade;" ,"™");//			знакторговоймарки
        txt = txt.replace("&deg;"   ,"°");//		градус
        txt = txt.replace("&plusmn;","±");//		плюс-минус
        txt = txt.replace("&frac14;","¼");//		дробь-одначетверть
        txt = txt.replace("&frac12;","½");//		дробь-однавторая
        txt = txt.replace("&frac34;","¾");//		дробь-тричетверти
        txt = txt.replace("&times;" ,"×");//		знакумножения
        txt = txt.replace("&divide;","÷");//		знакделения
        txt = txt.replace("&fnof;"  ,"ƒ");//		знакфункции
        txt = txt.replace("&Alpha;" ,"Α");//		греческаязаглавнаябукваальфа
        txt = txt.replace("&Beta;"  ,"Β");//		греческаязаглавнаябуквабета
        txt = txt.replace("&Gamma;" ,"Γ");//		греческаязаглавнаябуквагамма
        txt = txt.replace("&Delta;" ,"Δ");//		греческаязаглавнаябуквадельта
        txt = txt.replace("&Zeta;"  ,"Ζ");//		греческаязаглавнаябуквадзета
        txt = txt.replace("&Eta;"   ,"Η");//		греческаязаглавнаябукваэта
        txt = txt.replace("&Theta;" ,"Θ");//		греческаязаглавнаябукватета
        txt = txt.replace("&Iota;"  ,"Ι");//		греческаязаглавнаябукваиота
        txt = txt.replace("&Kappa;" ,"Κ");//		греческаязаглавнаябуквакаппа
        txt = txt.replace("&Lambda;","Λ");//		греческаязаглавнаябуквалямбда
        txt = txt.replace("&Mu;"    ,"Μ");//		греческаязаглавнаябуквамю
        txt = txt.replace("&Nu;"    ,"Ν");//		греческаязаглавнаябукваню
        txt = txt.replace("&Xi;"    ,"Ξ");//		греческаязаглавнаябуквакси
        txt = txt.replace("&Pi;"    ,"Π");//		греческаязаглавнаябуквапи
        txt = txt.replace("&Rho;"   ,"Ρ");//		греческаязаглавнаябукваро
        txt = txt.replace("&Sigma;" ,"Σ");//		греческаязаглавнаябуквасигма
        txt = txt.replace("&Tau;"   ,"Τ");//		греческаязаглавнаябукватау
        txt = txt.replace("&Phi;"   ,"Φ");//		греческаязаглавнаябуквафи
        txt = txt.replace("&Chi;"   ,"Χ");//		греческаязаглавнаябуквахи
        txt = txt.replace("&Psi;"   ,"Ψ");//		греческаязаглавнаябуквапси
        txt = txt.replace("&Omega;" ,"Ω");//		греческаязаглавнаябукваомега
        txt = txt.replace("&alpha;" ,"α");//		греческаястрочнаябукваальфа
        txt = txt.replace("&beta;"  ,"β");//		греческаястрочнаябуквабета
        txt = txt.replace("&gamma;" ,"γ");//		греческаястрочнаябуквагамма
        txt = txt.replace("&delta;" ,"δ");//		греческаястрочнаябуквадельта
        txt = txt.replace("&zeta;"  ,"ζ");//		греческаястрочнаябуквадзета
        txt = txt.replace("&eta;"   ,"η");//		греческаястрочнаябукваэта
        txt = txt.replace("&theta;" ,"θ");//		греческаястрочнаябукватета
        txt = txt.replace("&iota;"  ,"ι");//		греческаястрочнаябукваиота
        txt = txt.replace("&kappa;" ,"κ");//		греческаястрочнаябуквакаппа
        txt = txt.replace("&lambda;","λ");//		греческаястрочнаябуквалямбда
        txt = txt.replace("&mu;"    ,"μ");//		греческаястрочнаябуквамю
        txt = txt.replace("&nu;"    ,"ν");//		греческаястрочнаябукваню
        txt = txt.replace("&xi;"    ,"ξ");//		греческаястрочнаябуквакси
        txt = txt.replace("&pi;"    ,"π");//		греческаястрочнаябуквапи
        txt = txt.replace("&rho;"   ,"ρ");//		греческаястрочнаябукваро
        txt = txt.replace("&sigmaf;","ς");//		греческаястрочнаябуквасигма
        txt = txt.replace("&sigma;" ,"σ");//		греческаястрочнаябуквасигма
        txt = txt.replace("&tau;"   ,"τ");//		греческаястрочнаябукватау
        txt = txt.replace("&phi;"   ,"φ");//		греческаястрочнаябуквафи
        txt = txt.replace("&chi;"   ,"χ");//		греческаястрочнаябуквахи
        txt = txt.replace("&psi;"   ,"ψ");//		греческаястрочнаябуквапси
        txt = txt.replace("&omega;" ,"ω");//		греческаястрочнаябукваомега
        txt = txt.replace("&larr;"  ,"←");//	стрелкавлево
        txt = txt.replace("&uarr;"  ,"↑");//	стрелкавверх
        txt = txt.replace("&rarr;"  ,"→");//	стрелкавправо
        txt = txt.replace("&darr;"  ,"↓");//	стрелкавниз
        txt = txt.replace("&harr;"  ,"↔");//	стрелкавлево-вправо
        txt = txt.replace("&spades;","♠");//	знак масти "пики"
        txt = txt.replace("&clubs;" ,"♣");//	знак масти "трефы"
        txt = txt.replace("&hearts;","♥");//	знак масти "червы"
        txt = txt.replace("&diams;" ,"♦");//	знак масти "бубны"
        txt = txt.replace("&quot;"  ,"\"");//	двойная кавычка
        txt = txt.replace("&amp;"   ,"&");//	амперсанд

        txt = txt.replace("&hellip;","…");//	многоточие ...
        txt = txt.replace("&prime;" ,"′");//	одиночный штрих - минуты и футы
        txt = txt.replace("&Prime;" ,"″");//	двойной штрих - секунды и дюймы
        txt = txt.replace("&ndash;" ,"–");//	тире
        txt = txt.replace("&mdash;" ,"—");//	длинное тире
        txt = txt.replace("&lsquo;" ,"‘");//	левая одиночная кавычка
        txt = txt.replace("&rsquo;" ,"’");//	правая одиночная кавычка
        txt = txt.replace("&sbquo;" ,"‚");//	нижняя одиночная кавычка
        txt = txt.replace("&ldquo;" ,"“");//	левая двойная кавычка
        txt = txt.replace("&rdquo;" ,"”");//	правая двойная кавычка
        txt = txt.replace("&bdquo;" ,"„");//	нижняя двойная кавычка
        txt = txt.replace("&laquo;" ,"«");//	левая двойная угловая скобка
        txt = txt.replace("&raquo;" ,"»");//	правая двойная угловая скобка

        txt = txt.replace("&lt;"    ,"<");//	знак "меньше"
        txt = txt.replace("&gt;"    ,">");//	знак "больше"


        txt = txt.replace("&Epsilon;","Ε");//		греческаязаглавнаябукваэпсилон
        txt = txt.replace("&Omicron;","Ο");//		греческаязаглавнаябукваомикрон
        txt = txt.replace("&Upsilon;","Υ");//		греческаязаглавнаябукваипсилон
        txt = txt.replace("&epsilon;","ε");//		греческаястрочнаябукваэпсилон
        txt = txt.replace("&omicron;","ο");//		греческаястрочнаябукваомикрон
        txt = txt.replace("&upsilon;","υ");//		греческаястрочнаябукваипсилон

        return txt;
    }
}
