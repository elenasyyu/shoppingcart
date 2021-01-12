export class ShoppingCartUtils {
    static isEmpty(input: string): boolean {
        if (input == null) 
            return true;

        if (input.length <= 0)
            return true;

        if (input === "")
            return true;

        return false;
    }
}