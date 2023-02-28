package com.system.contractDetails;

import com.system.model.Company;
import com.system.model.OrderDetails;
import com.system.model.Product;
import com.system.service.CompanyService;
import com.system.service.impl.CompanyServiceImpl;

public class Contract {

    private final CompanyService companyService = new CompanyServiceImpl();
    private final Company company = companyService.getAll().get(0);

    public String getSoldContract(OrderDetails orderDetails) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("                                          ДОГОВОР КУПЛИ-ПРОДАЖИ № " + orderDetails.getOrderId() + "\n");
        stringBuilder.append("г. Могилев                                                                                    «_____»_______________20   \n");
        stringBuilder.append(company.getName() + " именуемый в дальнейшем \n");
        stringBuilder.append("\"Продавец\", в лице директора Иванова И.И., действующего на основании Устава,\n" +
                "с одной стороны, и," + orderDetails.getCustomer().getName() + "именуемое в \n" +
                "дальнейшем «Покупатель», в лице директора Петрова С.С., действующего на\n" +
                " основании Устава, с другой стороны, заключили настоящий Договор о \n" +
                " нижеследующем: \n" +
                "                                           1.ПРЕДМЕТ ДОГОВОРА\n" +
                "1.1.Продавец обязуется передать (поставить) Покупателю имущество, именуемое в\n" +
                " дальнейшем «товар» по наименованию и количеству согласно Спецификации- \n " +
                "Протокола согласования свободных договорных цен на товары (Приложение №1) и в \n" +
                "соответствии с Техническими требованиями (Приложение №2), являющимися \n" +
                "неотъемлемыми частями настоящего Договора.\n" +
                "1.2.Цель приобретения товара - для собственных нужд.\n" +
                "1.3.Упаковка товара должна обеспечивать сохранность товара при перевозке,\n" +
                " погрузочно-разгрузочных работах и хранении.\n" +
                "1.4.Продавец гарантирует, что на момент заключения Договора товар является \n" +
                " новым, не был в употреблении, в споре и под арестом не состоит, не является \n" +
                "предметом залога и не обременен другими правами третьих лиц.\n" +
                "1.5.Продавец обязуется поставить качественный товар, соответствующий условиям \n" +
                "договора.\n" +
                "1.6.По соглашению сторон объем (количество) товара может быть изменен в пределах \n" +
                " десяти процентов.\n" +
                "                                   2. АДРЕСА, РЕКВИЗИТЫ И ПОДПИСИ СТОРОН \n" +
                "Продавец:                                                        Покупатель: \n" +
                "Адрес:                                                               Адрес:\n" +
                company.getAddress() + "            " + orderDetails.getCustomer().getAddress() + "\n" +
                "тел. +" + company.getPhoneNumber() + "                                         тел. +" + orderDetails.getCustomer().getPhoneNumber() + "\n" +
                "УНП " + company.getInn() + "                                                    УНП " + orderDetails.getCustomer().getInn() + "\n" +
                "р/с " + company.getIban() + "                    р/с  " + orderDetails.getCustomer().getIban() + "\n" +
                "                                         3. ПОСТАВЛЯЕМАЯ ПРОДУКЦИЯ \n");
        for (Product product : orderDetails.getProductList()) {
            stringBuilder.append("- Название: ")
                    .append(product.getName())
                    .append(", количество шт: ")
                    .append(product.getCount())
                    .append(", цена за шт")
                    .append(product.getPrice())
                    .append(",\n общая стоимость: ")
                    .append(product.getCount() * product.getPrice())
                    .append("\n");
        }


        return stringBuilder.toString();
    }

    public String getOrderContract(OrderDetails orderDetails) {
        Integer count = 0;
        Double coastWithoutPer = 0.0;
        Double coastWithPer = 0.0;
        Double persCount = 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\tГрузоотправитель\tГрузополучатель\n" +
                "\t\t" + company.getInn() + "\t\t" + orderDetails.getCustomer().getInn() + "\n" +
                "\t\t\tУНП\n" +
                "\t\t\t\"_____\"____________ 20\tг.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "Грузоотправитель " + company.getName() + ", " + company.getAddress() + "\n" +
                "(наименование, адрес)\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "Грузополучатель " + orderDetails.getCustomer().getName() + ", " + orderDetails.getCustomer().getAddress() + "\n" +
                "(наименование, адрес)\t\t\t\t\t\t\t\n" +
                "Основание отпуска\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t(наименование, дата и номер документа)\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\tI. ТОВАРНЫЙ РАЗДЕЛt\t\t\n" +
                "Наименование товара Единица измерения Коли-чество Цена, руб. коп. Стоимость, руб. коп. Ставка НДС, % Сумма НДС, руб. коп. Стоимость с НДС, руб. коп. Приме-чание \n" +
                "\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t\n");
        for (Product product : orderDetails.getProductList()) {
            stringBuilder.append("\t").append(product.getName())
                    .append("\t\t шт").append("\t\t").append(product.getCount()).append("\t\t").append(product.getPrice() / 1.2)
                    .append("\t\t 20%").append("\t\t").append(product.getPrice() / 1.8).append("\t\t").append(product.getName())
                    .append("\t\t" + product.getPrice() * 1.2);
            count += product.getCount();
            coastWithoutPer += product.getPrice() / 1.2;
            coastWithPer += product.getPrice();
            persCount += product.getPrice() / 1.8;

        }
        stringBuilder.append("\n ИТОГО");
        stringBuilder.append("\t").append(count);
        stringBuilder.append("\t").append(coastWithoutPer);
        stringBuilder.append("\t").append(persCount);
        stringBuilder.append("\t").append(coastWithPer);
        stringBuilder.append("\nВсего сумма НДС\t\t\tруб.\t\t\tкоп.\t\n" +
                "\t\t\t(прописью)\t\t\t(цифрами)\n" +
                "Всего стоимость с НДС\t\t\t\tруб.\t\t\t\tкоп.\t\n" +
                "\t\t\t(прописью)\t\t\t(цифрами)\t\n" +
                "Отпуск разрешил\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t(должность, фамилия, инициалы, подпись)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "Сдал грузоотправитель\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t(должность, фамилия, инициалы, \t\t\t\t\t\t\t\t\t\t\t\n" +
                "подпись; штамп (печать) грузоотправителя)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "Товар к доставке принял\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t(должность, фамилия, инициалы, подпись)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "по доверенности\t\t\t\t,\tвыданной\n" +
                "\t\t\t(номер, дата)\t\t(наименование организации)\n" +
                "Принял грузополучатель\n" +
                "\t\t\t(должность, фамилия и инициалы, \n" +
                "подпись; штамп (печать) грузополучателя)\n");
        return stringBuilder.toString();
    }

}
