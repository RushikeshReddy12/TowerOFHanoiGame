import java.util.*;

public class TowerOfHanoiGameUsingJava {
    static int NOD;

    static class Move {
        char from, to;
        Move next;
    }

    static Move track = null;

    static class Node {
        int data;
        Node next;
    }

    static Node source = null, auxiliary = null, destination = null, nn = null, temp = null, head = null, current = null;

    static Node createNode(int NOD) {
        Node node = new Node();
        node.data = NOD;
        node.next = null;
        return node;
    }

    static void insertEnd(int value) {
        nn = createNode(value);
        if (head == null) {
            head = nn;
        } else {
            temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nn;
        }
    }

    static void printSource() {
        temp = source;
        int val = 0, i, j;
        while (temp != null) {
            val = temp.data;
            for (i = 1; i <= 3; ++i) {
                for (j = 1; j <= val * 3; ++j) {
                    if (i == 1 || i == 3 || j == 1 || j == val * 3)
                        System.out.print(" *");
                    else
                        System.out.print("  ");
                }
                System.out.println();
            }
            temp = temp.next;
        }
        char str1, str2;
        str1 = '_';
        str2 = ' ';
        for (i = 0; i <= val * 6; ++i) {
            System.out.print(str1);
        }
        System.out.println();
        for (i = 0; i <= val * 3; ++i) {
            System.out.print(str2);
        }
        System.out.println("A");
        for (i = 0; i < NOD * 3; ++i)
            System.out.println();
    }

    static void printAuxiliary() {
        temp = auxiliary;
        int val=0, i, j;
        while (temp != null) {
            val = temp.data;
            for (i = 1; i <= 3; ++i) {
                for (j = 1; j <= val * 3; ++j) {
                    if (i == 1 || i == 3 || j == 1 || j == val * 3)
                        System.out.print(" *");
                    else
                        System.out.print("  ");
                }
                System.out.println();
            }
            temp = temp.next;
        }
        char str1, str2;
        str1 = '_';
        str2 = ' ';
        for (i = 0; i <= val * 6; ++i) {
            System.out.print(str1);
        }
        System.out.println();
        for (i = 0; i <= val * 3; ++i) {
            System.out.print(str2);
        }
        System.out.println("B");
        for (i = 0; i < NOD * 3; ++i)
            System.out.println();
    }

    static void printDestination() {
        temp = destination;
        int val = 0, i, j;
        while (temp != null) {
            val = temp.data;
            for (i = 1; i <= 3; ++i) {
                for (j = 1; j <= val * 3; ++j) {
                    if (i == 1 || i == 3 || j == 1 || j == val * 3)
                        System.out.print(" *");
                    else
                        System.out.print("  ");
                }
                System.out.println();
            }
            temp = temp.next;
        }
        char str1, str2;
        str1 = '_';
        str2 = ' ';
        for (i = 0; i <= val *6; ++i) {
            System.out.print(str1);
        }
        System.out.println();
        for (i = 0; i <= val * 3; ++i) {
            System.out.print(str2);
        }
        System.out.println("C\n");
    }

    static void printDisks() {
        printSource();
        printAuxiliary();
        printDestination();
    }

    static void initialize() {
        for (int i = 1; i <= NOD; ++i) {
            insertEnd(i);
        }
        source = head;
        printDisks();
    }

    static boolean completeDestination() {
        int count = 0;
        temp = destination;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count == NOD;
    }

    static void move(int from, int to) {
        int val;
        if (from == 1 && to == 2) {
            if (source == null) {
                System.out.println("No disks in A");
                return;
            }
            if (auxiliary != null) {
                val = source.data;
                if (auxiliary.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of B");
                } else {
                    source = source.next;
                    nn = createNode(val);
                    nn.next = auxiliary;
                    auxiliary = nn;
                }
            } else {
                val = source.data;
                source = source.next;
                nn = createNode(val);
                auxiliary = nn;
            }
        }
        if (from == 1 && to == 3) {
            if (source == null) {
                System.out.println("No disks in A");
                return;
            }
            if (destination != null) {
                val = source.data;
                if (destination.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of C");
                } else {
                    source = source.next;
                    nn = createNode(val);
                    nn.next = destination;
                    destination = nn;
                }
            } else {
                val = source.data;
                source = source.next;
                nn = createNode(val);
                destination = nn;
            }
        }
        if (from == 2 && to == 1) {
            if (auxiliary == null) {
                System.out.println("No disks in B");
                return;
            }
            if (source != null) {
                val = auxiliary.data;
                if (source.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of A");
                } else {
                    auxiliary = auxiliary.next;
                    nn = createNode(val);
                    nn.next = source;
                    source = nn;
                }
            } else {
                val = auxiliary.data;
                auxiliary = auxiliary.next;
                nn = createNode(val);
                source = nn;
            }
        }
        if (from == 2 && to == 3) {
            if (auxiliary == null) {
                System.out.println("No disks in B");
                return;
            }
            if (destination != null) {
                val = auxiliary.data;
                if (destination.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of C");
                } else {
                    auxiliary = auxiliary.next;
                    nn = createNode(val);
                    nn.next = destination;
                    destination = nn;
                }
            } else {
                val = auxiliary.data;
                auxiliary = auxiliary.next;
                nn = createNode(val);
                destination = nn;
            }
        }
        if (from == 3 && to == 1) {
            if (destination == null) {
                System.out.println("No disks in C");
                return;
            }
            if (source != null) {
                val = destination.data;
                if (source.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of A");
                } else {
                    destination = destination.next;
                    nn = createNode(val);
                    nn.next = source;
                    source = nn;
                }
            } else {
                val = destination.data;
                destination = destination.next;
                nn = createNode(val);
                source = nn;
            }
        }
        if (from == 3 && to == 2) {
            if (destination == null) {
                System.out.println("No disks in C");
                return;
            }
            if (auxiliary != null) {
                val = destination.data;
                if (auxiliary.data < val) {
                    System.out.println("You cannot move this element, because it is bigger than the top of B");
                } else {
                    destination = destination.next;
                    nn = createNode(val);
                    nn.next = auxiliary;
                    auxiliary = nn;
                }
            } else {
                val = destination.data;
                destination = destination.next;
                nn = createNode(val);
                auxiliary = nn;
            }
        }
    }

    static void exit() {
        System.out.println("Thank you.");
        System.out.println("Have a nice day.");
    }

    static void play() {
    	int count=0, i=0, k=0;
    	int[] stack = new int[20];
    	Scanner scanner = new Scanner(System.in);
        System.out.println("\nHOW TO PLAY:\n");
        System.out.println("1. Choose the number of disks you want to play with.");
        System.out.println("2. The disks entered will be displayed on the monitor.");
        System.out.println("3. To move a disk from one stack to another enter the from and to.");
        System.out.println("Do you want to start?\n Press 1 to start\n Press 0 to exit");
        int ch = scanner.nextInt();
        if (ch == 0) {
            exit();
        } else {
            System.out.println("\nEnter the number of disks you want to play with: ");
            NOD = scanner.nextInt();
            initialize();
            int from, to;
            while (!completeDestination()) {
            	count++;
                System.out.println("Enter From: ");
                from = scanner.nextInt();
                System.out.println("Enter To: ");
                to = scanner.nextInt();
                System.out.println();
                stack[k++]=from;
                stack[k++]=to;
                move(from, to);
                printDisks();
            }
            System.out.println("CONGRATULATIONS\nYOU HAVE SUCCESSFULLY MOVED ALL DISKS FROM A TO C.");
            System.out.println("YOU HAVE COMPLETED THE GAME WITH " + count + " MOVES.");
            System.out.println("YOUR MOVES ARE:");
            while(i<=((count+count)-1))
            {
            	System.out.println(stack[i]+" "+stack[i+1]);
            	i+=2;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nTOWER OF HANOI\n");
        System.out.println("ABOUT GAME:");
        System.out.println("The Tower of Hanoi is a classic mathematical puzzle that involves three rods and a set of disks of different sizes.");
        System.out.println("The objective is to move the entire stack of disks from one rod to another, adhering to specific rules.");
        System.out.println("Only one disk can be moved at a time, and a larger disk cannot be placed on top of a smaller one.");
        System.out.println("The challenge lies in finding the most efficient way to transfer the entire stack while minimizing the number of moves.");
        System.out.println("The Tower of Hanoi not only serves as a recreational game but also as a metaphor for problem-solving strategies in computer science and algorithm design.");
        System.out.println("\n\nINSTRUCTIONS");
        System.out.println("1. Only one disk can be moved at a time");
        System.out.println("2. A disk can only be placed on top of a larger disk or an empty rod");
        System.out.println("3. Only the top of a disk can be moved, cannot move a disk which is in the middle");

        System.out.println("\nDo you wish to play?");
        System.out.println("Press 1 to play\nPress 0 to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                play();
                break;
            case 0:
                exit();
                System.exit(0);
        }
        scanner.close();
    }
}
