class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class TowerOfHanoi:
    def __init__(self):
        self.NOD = 0
        self.source = None
        self.auxiliary = None
        self.destination = None
        self.stack = []
    
    def create_node(self, data):
        return Node(data)
    
    def insert_end(self, value):
        nn = self.create_node(value)
        if not self.source:
            self.source = nn
        else:
            temp = self.source
            while temp.next:
                temp = temp.next
            temp.next = nn
    
    def print_tower(self, tower, label):
        if not tower:
            print("-")
            print(label)
            print("\n"*5)
            return
        temp = tower
        val = 0
        while temp:
            val = temp.data
            for i in range(1, 4):
                for j in range(1, val * 3 + 1):
                    if i == 1 or i == 3 or j == 1 or j == val * 3:
                        print(" *", end="")
                    else:
                        print("  ", end="")
                print()
            temp = temp.next
        if val > 0:
            print("_" * (val * 6))
            print(" " * (val * 3) + label)
        print("\n"*5)
    
    def print_disks(self):
        self.print_tower(self.source, "A")
        self.print_tower(self.auxiliary, "B")
        self.print_tower(self.destination, "C")
    
    def initialize(self):
        for i in range(1, self.NOD + 1):
            self.insert_end(i)
        self.print_disks()
    
    def complete_destination(self):
        count = 0
        temp = self.destination
        while temp:
            temp = temp.next
            count += 1
        return count == self.NOD
    
    def move(self, from_tower, to_tower):
        from_tower_ref = {1: "source", 2: "auxiliary", 3: "destination"}
        to_tower_ref = {1: "source", 2: "auxiliary", 3: "destination"}

        from_node = getattr(self, from_tower_ref[from_tower])
        to_node = getattr(self, to_tower_ref[to_tower])
        
        if not from_node:
            print(f"No disks in {from_tower_ref[from_tower].upper()}")
            return
        
        if to_node and from_node.data > to_node.data:
            print("You cannot move this element, because it is bigger than the top of the destination tower.")
            return
        
        # Move disk
        new_node = self.create_node(from_node.data)
        new_node.next = to_node
        setattr(self, to_tower_ref[to_tower], new_node)
        setattr(self, from_tower_ref[from_tower], from_node.next)
    
    def hanoi(self, n, from_tower, aux_tower, to_tower):
        if n == 1:
            self.stack.append((from_tower, to_tower))
            return
        self.hanoi(n - 1, from_tower, to_tower, aux_tower)
        self.stack.append((from_tower, to_tower))
        self.hanoi(n - 1, aux_tower, from_tower, to_tower)
    
    def play(self):
        print("Do you want to start?")
        print("Press 1 to start\nPress 0 to exit")
        choice = int(input())
        if choice == 0:
            print("Thank you. Have a nice day.")
            return
        
        print("\nEnter the number of disks you want to play with:")
        self.NOD = int(input())
        self.initialize()
        self.hanoi(self.NOD, 1, 2, 3)
        
        for move in self.stack:
            self.move(move[0], move[1])
            self.print_disks()
        
        print("CONGRATULATIONS\nYOU HAVE SUCCESSFULLY MOVED ALL DISKS FROM A TO C.")
    
if __name__ == "__main__":
    print("\nTOWER OF HANOI\n")
    print("ABOUT GAME:")
    print("The Tower of Hanoi is a classic mathematical puzzle...")
    print("\n\nINSTRUCTIONS")
    print("1. Only one disk can be moved at a time")
    print("2. A disk can only be placed on top of a larger disk or an empty rod")
    print("3. Only the top of a disk can be moved, cannot move a disk which is in the middle")
    game = TowerOfHanoi()
    game.play()
