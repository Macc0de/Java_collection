package lab03;

public class Main {
    public static void format_print(TouristBus bus, int num) {
        int real_profit = 11000;
        int seats = bus.getSeats();
        int reserved_seats = bus.getReservedSeats();
        int free_seats = bus.getFreeSeats();
        int total_cost = bus.getTotalCost();
        boolean profit = bus.isProfit(real_profit);

        System.out.printf("Автобус %d:\nВсего мест: %d, Занято мест: %d, " +
                "Свободно: %d\nСтоимость всех занятых мест: %d\n", num, seats, reserved_seats,
                free_seats, total_cost);

        if(profit)
            System.out.print("Поездка выгодна!");
        else
            System.out.print("Поездка НЕ выгодна!");
    }

    public static void main(String[] args) {
        // места, стоимость одного места
        TouristBus bus1 = new TouristBus(54, 400);
        TouristBus bus2 = new TouristBus(45, 500);
        //TouristBus bus3 = new TouristBus(bus2); Копирование

        int group1 = 25;
        int group2 = 30;

        bus1.setReservedSeats(group1);
        bus2.setReservedSeats(group2);

        format_print(bus1, 1);
        System.out.printf("\n\n");
        format_print(bus2, 2);
    }
}
