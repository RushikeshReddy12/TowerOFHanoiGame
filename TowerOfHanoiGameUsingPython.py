def create_node(data):
    return {'data': data, 'next': None}

def insert_end(head, value):
    node = create_node(value)
    if head is None:
        return node
    temp = head
    while temp['next'] is not None:
        temp = temp['next']
    temp['next'] = node
    return head

def print_stack(stack, label):
    temp = stack
    val = 0
    while temp is not None:
        val = temp['data']
        for i in range(1, 4):
            for j in range(1, val * 3 + 1):
                if i == 1 or i == 3 or j == 1 or j == val * 3:
                    print('*', end=' ')
                else:
                    print(' ', end=' ')
            print()
        temp = temp['next']

    for _ in range(val * 6 + 1):
        print('_', end='')
    print()

    for _ in range(val * 3 + 1):
        print(' ', end='')
    print(label)

    for _ in range(NOD * 3):
        print()

def print_disks():
    print_stack(source, 'A')
    print_stack(auxiliary, 'B')
    print_stack(destination, 'C')

def initialize():
    global source
    global auxiliary
    global destination
    global NOD

    source = None
    auxiliary = None
    destination = None

    for i in range(1, NOD + 1):
        source = insert_end(source, i)
    print_disks()

def complete_destination():
    count = 0
    temp = destination
    while temp is not None:
        temp = temp['next']
        count += 1
    return count == NOD

def move(from_stack, to_stack):
    global source
    global auxiliary
    global destination

    val = None
    if from_stack == 1 and source is not None:
        val = source['data']
        source = source['next']
    elif from_stack == 2 and auxiliary is not None:
        val = auxiliary['data']
        auxiliary = auxiliary['next']
    elif from_stack == 3 and destination is not None:
        val = destination['data']
        destination = destination['next']
    else:
        print("Invalid move")
        return

    if to_stack == 1:
        if source is not None and source['data'] < val:
            print("Invalid move: Cannot place a larger disk on a smaller disk")
            return
        temp = create_node(val)
        temp['next'] = source
        source = temp
    elif to_stack == 2:
        if auxiliary is not None and auxiliary['data'] < val:
            print("Invalid move: Cannot place a larger disk on a smaller disk")
            return
        temp = create_node(val)
        temp['next'] = auxiliary
        auxiliary = temp
    elif to_stack == 3:
        if destination is not None and destination['data'] < val:
            print("Invalid move: Cannot place a larger disk on a smaller disk")
            return
        temp = create_node(val)
        temp['next'] = destination
        destination = temp
    else:
        print("Invalid destination")

def play():
    global NOD
    global source
    global auxiliary
    global destination

    stack = []
    print("\nHOW TO PLAY:\n")
    print("1. Choose the number of disks you want to play with.")
    print("2. The disks entered will be displayed on the monitor.")
    print("3. To move a disk from one stack to another, enter the source and destination stack numbers.")
    
    choice = int(input("Do you want to start?\nPress 1 to start\nPress 0 to exit\n"))
    if choice == 0:
        print("Thank you. Have a nice day.")
        return

    NOD = int(input("\nEnter the number of disks you want to play with: "))
    initialize()
    count = 0

    while not complete_destination():
        count += 1
        print("Enter Source (1 for A, 2 for B, 3 for C): ", end='')
        from_stack = int(input())
        print("Enter Destination (1 for A, 2 for B, 3 for C): ", end='')
        to_stack = int(input())
        stack.append((from_stack, to_stack))
        move(from_stack, to_stack)
        print_disks()

    print("CONGRATULATIONS!\nYOU HAVE SUCCESSFULLY MOVED ALL DISKS FROM A TO C.")
    print(f"YOU COMPLETED THE GAME IN {count} MOVES.")
    print("YOUR MOVES ARE:")
    for move_pair in stack:
        print(f"{move_pair[0]} {move_pair[1]}")

if __name__ == "__main__":
    print("\nTOWER OF HANOI\n")
    print("ABOUT THE GAME:")
    print("The Tower of Hanoi is a classic mathematical puzzle with three rods and a set of disks of different sizes.")
    print("The objective is to move the stack of disks from one rod to another following specific rules.")
    print("Rules:\n1. Only one disk can be moved at a time.\n2. A larger disk cannot be placed on a smaller disk.\n3. Only the top disk of a stack can be moved.")

    choice = int(input("\nDo you wish to play?\nPress 1 to play\nPress 0 to exit\n"))
    if choice == 1:
        play()
    else:
        print("Thank you. Have a nice day.")
