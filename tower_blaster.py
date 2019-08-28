import random

def setup_bricks():
    """
        Create a main pile of 60 bricks, represented as a list containing the integers 1 – 60.
        Create a discard pile of 0 bricks, represented as an empty list.
        Return 2 lists above as a tuple.
    """
    main_pile = [i for i in range(1, 61)]  # containing integers 1-60
    discard = []
    return main_pile, discard  # return a tuple consisting of 2 lists

def shuffle_bricks(bricks):
    """
        Shuffle the given bricks with random function
    """
    random.shuffle(bricks)  # Shuffle the list

def check_bricks(main_pile, discard):
    """
        If there are no cards left in the main pile, shuffle the discard pile and move those bricks
        to the main pile and the top card will be the start of the new discard pile
    """
    if len(main_pile) == 0:  # if main_pile is empty
        random.shuffle(discard)  # Shuffle discard
        for i in discard:
            main_pile.append(i)  # move the bricks of discard to the main_pile
        discard.clear()  # clear discard
        discard.append(get_top_brick(main_pile))  # turn over the top card to be the start of the new discard pile

def check_tower_blaster(tower):
    """
        Given the user’s or the computer’s list, determine if bricks are in ascending order.
    """
    if sorted(tower) == tower:  # if bricks are in ascending order
        return True
    else:
        return False

def get_top_brick(brick_pile):
    """
        Remove and return the top brick from any given pile of bricks
    """
    top_brick = brick_pile[0]  # get the top brick of a given pile
    brick_pile.pop(0)  # and remove it from the pile
    return top_brick

def deal_initial_bricks(main_pile):
    """
        Deal two sets of 10 bricks each from the given main_pile following the normal conventions of dealing.
        The computer is always the first person that gets dealt to and always plays first.
        Place the bricks one on top of the other.
    """
    computer_list = []
    user_list = []  # initialize computer's and user's tower

    for i in range(10):
        computer_list.append(get_top_brick(main_pile))  # add the current top brick of main_pile to computer's list
        user_list.append(get_top_brick(main_pile))  # add the current top brick of main_pile to user's list
    computer_list.reverse()  # since we have to place bricks one on top of the other
    user_list.reverse()
    return computer_list, user_list  # return a tuple consisting of 2 lists

def add_brick_to_discard(brick, discard):
    """
        Add the given brick to the top of the given discard pile
    """
    discard.insert(0, brick)  # insert at top

def find_and_replace(new_brick, brick_to_be_replaced, tower, discard):
    """
        Find the given brick to be replaced in the given tower and replace it with the given new brick.
    """
    if brick_to_be_replaced in tower:  # if the brick_to_be_replaced is in the tower
        add_brick_to_discard(brick_to_be_replaced, discard)
        # The given brick to be replaced then gets put on top of the given discard pile.
        x = tower.index(brick_to_be_replaced)  # get the index of the brick_to_be_replaced
        tower[x] = new_brick  # replace with the new brick

        return True
    else:
        return False

def computer_play(tower, main_pile, discard):
    """
        Computer’s strategy: Divide the tower into 6 parts, each part is corresponding to 6 numbers in order.
        According to the top of the pile, put it into the corresponding position
    """
    brick = discard[0]  # first check the top of discard then decide how to do with it
    if brick in range(1, 7):
        computer_discard(0, 1, 6, tower, discard, main_pile)  # decide the following decisions

    if brick in range(7, 13):
        computer_discard(1, 7, 12, tower, discard, main_pile)  # decide the following decisions

    if brick in range(13, 19):
        computer_discard(2, 13, 18, tower, discard, main_pile)  # decide the following decisions

    if brick in range(19, 25):
        computer_discard(3, 19, 24, tower, discard, main_pile)  # decide the following decisions

    if brick in range(25, 31):
        computer_discard(4, 25, 30, tower, discard, main_pile)  # decide the following decisions

    if brick in range(31, 37):
        computer_discard(5, 31, 36, tower, discard, main_pile)  # decide the following decisions

    if brick in range(37, 43):
        computer_discard(6, 37, 42, tower, discard, main_pile)  # decide the following decisions

    if brick in range(43, 49):
        computer_discard(7, 43, 48, tower, discard, main_pile)  # decide the following decisions

    if brick in range(49, 55):
        computer_discard(8, 49, 54, tower, discard, main_pile)  # decide the following decisions

    if brick in range(55, 61):
        computer_discard(9, 55, 60, tower, discard, main_pile)  # decide the following decisions
    return tower


def computer_discard(position, start, end, tower, discard, main_pile):
    """
        The computer decides to use whether the top of discard or the top of main_pile.
        If the given position already has a corresponding number, then choose the top of main_pile.
        If not, use the top of discard
    """
    if start <= tower[position] <= end:  # check if the given position has a corresponding number
        top_main = get_top_brick(main_pile)  # if so, turn to top of main_pile
        pos = (top_main - 1) // 6  # get the corresponding position where the top of main_pile should be put
        computer_main(pos, 6 * pos + 1, 6 * pos + 6, tower, discard, top_main)
        # use the function that deals with top of main_pile
    else:
        brick = get_top_brick(discard)  # if the given position dosen't have a corresponding number
        find_and_replace(brick, tower[position], tower, discard)  # replace it
    return tower

def computer_main(position, start, end, tower, discard, top_main):
    """
        If the given position already has a corresponding number, then drop the top of main_pile.
        If not, use the top of main_pile
    """
    if start <= top_main <= end:  # check if the given position has a corresponding number
        add_brick_to_discard(top_main, discard)  # if so, drop the top of main_pile
    else:
        find_and_replace(top_main, tower[position], tower, discard)  # if not, replace it



def user_play_discard(user_tower, position, discard):
    """
        If the user chooses the top of discard, replace the certain number
    """
    new_brick = get_top_brick(discard)  # get the top of discard
    find_and_replace(new_brick, user_tower[position], user_tower, discard)
    # Find the given brick to be replaced in the given tower and replace it with the given new brick.
    return user_tower

def user_play_main(user_tower, position, discard, new_brick):
    """
        If the user chooses the top of main_pile, then replace the certain number
    """

    find_and_replace(new_brick, user_tower[position], user_tower, discard)
    # Find the given brick to be replaced in the given tower and replace it with the given new brick.
    return user_tower


def main():
    """
    To complete the whole functions of this game.
    """

    print("*"*25 + "Welcome to Tower Blaster!" + "*"*25)
    main_pile, discard = setup_bricks()  # initialize main_pile and discard
    shuffle_bricks(main_pile)  # shuffle main_pile
    computer_tower, user_tower = deal_initial_bricks(main_pile)  # initialize computer's and user's tower
    print("The initial tower of the computer:", computer_tower)
    print("Your initial tower:", user_tower)
    discard.append(get_top_brick(main_pile))  # turn over the top card to be the start of the new discard pile.
    playing = True  # keep the game running if it's True until we have a winner

    while playing:

        check_bricks(main_pile, discard)  # check if main_pile is empty


        print("*"*25 + "Now it's the computer's turn!" + "*"*25)

        computer_play(computer_tower, main_pile, discard)  # let the computer play

        if check_tower_blaster(computer_tower):  # if the tower of computer is in ascending order, it wins
            print("*"*25 + "The computer wins!" + "*"*25)
            playing = False
            break

        print("*"*25 + "Your turn!" + "*"*25)
        print("Your current tower:", user_tower)

        print("The top of the discard pile:", discard[0])
        print("*"*10 + "ATTENTION PLEASE: any input other than 'D' will be considered as 'M'!" + "*"*10)
        user_input = input("Chose {} to put into your tower?(Type 'D' to get this brick/ Type 'M' to get a mystery brick)".format(discard[0]))

        if user_input == 'D':
            x = input('Which brick would you like to replace?(0~9)')  # choose a position to replace
            x = int(x)
            user_play_discard(user_tower, x, discard)  # finish the swape

        else:  # user chooses a mystery brick
            check_bricks(main_pile, discard)  # in case that main_pile runs out in the middle
            top_main = get_top_brick(main_pile)  # get the top of main_pile
            print('The top brick in main pile :', top_main)  # showthe  user the top of main_pile
            print("*" * 10 + "ATTENTION PLEASE: any input other than 'Y' will be considered as 'N'!" + "*" * 10)
            user_input2 = input("Chose {} to put into your tower?(Type 'Y' for Yes/ Type 'N' for No)".format(top_main))

            if user_input2 == 'Y':
                x = input('Which brick would you like to replace?(0~9)')  # choose a position to replace
                x = int(x)
                user_play_main(user_tower, x, discard, top_main)  # finish the swape
            else:  # if user chooses to discard it
                add_brick_to_discard(top_main, discard)
                print('You gave up this round!')

        if check_tower_blaster(user_tower):  # check if the user wins
            print("Your final tower is:", user_tower)
            print("You win!")
            playing = False
            break


if __name__ == '__main__':
    main()