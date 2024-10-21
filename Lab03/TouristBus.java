package Lab03;

public class TouristBus {
    private int seats;
    private int cost_seat;
    private int reserved_seats;

    public TouristBus() {
        this.seats = 0;
        this.cost_seat = 0;
        this.reserved_seats = 0;
    }

    public TouristBus(int seats, int cost_seat) {
        setSeats(seats);
        setCostSeat(cost_seat);
        //this.seats = seats;
        //this.cost_seat = cost_seat;

        this.reserved_seats = 0;
    }

    public TouristBus(TouristBus new_bus) {
        this.seats = new_bus.seats;
        this.cost_seat = new_bus.cost_seat;
        this.reserved_seats = new_bus.reserved_seats;
    }

    // Методы изменяющие значения полей - сеттеры
    public void setSeats(int seats) {
        if(seats <= 0) {
            System.out.println("Кол-во мест должно быть положительным числом!");
            return;
        }
        this.seats = seats;
    }
    public void setCostSeat(int cost_seat) {
        if(cost_seat <= 0) {
            System.out.println("Стоимость места должна быть положительным числом!");
            return;
        }
        this.cost_seat = cost_seat;
    }
    public void setReservedSeats(int reserved_seats) {
        if(reserved_seats < 0 || reserved_seats > seats) {
            System.out.println("Слишком много занятых мест!");
            return;
            //throw new IllegalArgumentException("Некорректное значение занятых мест!");
        }
        this.reserved_seats = reserved_seats;
    }

    // Методы возвращающие значения полей - геттеры
    public int getSeats() {
        return seats;
    }
    public int getCostSeat() {
        return cost_seat;
    }
    public int getReservedSeats() {
        return reserved_seats;
    }

    // Метод рассчитывающий кол-во свободных мест
    public int getFreeSeats() {
        return seats - reserved_seats;
    }
    // Метод проверяющий пуст автобус или заполнен
    public int isEmptyBus() {
        return (reserved_seats == 0) ? 1 : 0;
    }
    // Метод рассчитывающий общую стоимость занятых мест
    public int getTotalCost() {
        return reserved_seats * cost_seat;
    }

    public boolean isProfit(int real_profit) {
        return getTotalCost() > real_profit;
    }
}


