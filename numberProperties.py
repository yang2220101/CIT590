def getFactor(x):
    """to get the factors of the number the user enters and return a list of factors
    """
    factors=[]#use this list to store the factors of given number
    x = int(x)#assume that input is positive integers and cast it into integer
    for i in range(1, x+1):
        if x % i == 0:#if x is divisible by i,then i is a factor of x
            factors.append(i)#if i is a factor of x,append it to the list 'factors'
    return factors

def lenFactor(x):
    """to get the factors of the number the user enters and return the number of factors
    """
    return len(getFactor(x))#to get the number of factors from list 'factors'

def sumFactor(x):
    """to get the factors of the number the user enters and return the sum of factors(the number itself not included)
    """
    return sum(getFactor(x)) - x#to get the sum of factors from list 'factors'
def isPrime(x):
    """Returns True if the number the user enters is a prime number,otherwise False.
    """
    if lenFactor(x) == 2:#if the number of factors is exactly 2,then x is a prime number
        return True
    else:#if the number of factors is not 2,then x is not a prime number
        return False

def isComposite(x):
    """Returns True if the number the user enters is a composite number,otherwise False.
    """
    if lenFactor(x) > 2:#if the number of factors is more than 2,then x is a composite number
        return True
    else:#if the number of factors is no greater than 2,then x is not a composite number
        return False

def isPerfect(x):
    """Returns True if the number the user enters is a perfect number,otherwise False.
    """
    if x == sumFactor(x):#if the sum of all x's factors except x itself is equal to x,then x is a perfect number
        return True
    else:#if the equation above doesn't hold,then x is not a perfect number
        return False

def isAbundant(x):
    """Returns True if the number the user enters is an abundant number,otherwise False.
    """
    if x < sumFactor(x):#if the sum of all x's factors except x itself is greater than x,then x is an abundant number
        return True
    else:##if the inequality above doesn't hold,then x is not an abundant number
        return False

def isTriangular(x):
    """Returns True if the number the user enters is a triangular number,otherwise False.
    """
    triangular = [(n * (n + 1)) / 2 for n in range(1, 142)]#to get all the triangular numbers no greater than 10000
    if x in triangular:#if x is in the list above,it is a triangular number,otherwise it is not
        return True
    else:
        return False

def isPentagonal(x):
    """Returns True if the number the user enters is a pentagonal number,otherwise False.
    """
    if isTriangular(x * 3):#every pentagonal number is 1/3 of a triangular number
        return True
    else:
        return False

def isHexagonal(x):
    """Returns True if the number the user enters is a hexagonal number,otherwise False.
    """
    hexagonal = [2 * n * n - n for n in range(1, 71)]#to get all the hexagonal numbers no greater than 10000
    if x in hexagonal:#if x is in the list above,it is a hexagonal number,otherwise it is not
        return True
    else:
        return False

def isNarcissistic(x):
    """Returns True if the number the user enters is a narcissistic number,otherwise False.
    """
    x = str(x)
    power = len(x)#to get the power by counting the number of digits
    digit = []#to get a list of each digit
    for i in x:
        j = int(i) ** power
        digit.append(j)
    s = sum(digit)#sum of its own digits each raised to the power of the number of digits
    if int(x) == s:#if the sum is equal to x,then x is a narcissistic number
        return True
    else:#if the equation above doesn't stand,then x is not a narcissistic number
        return False

def main():
    playing = True
    while playing == True:#keep the program running if the user wants play

        num_input = input('Give me a number from 1 to 10000.Type - 1 to exit. ')

        try:
            num = int(num_input)#cast the input into integer
            if (num == -1):
                playing = False#stop the program if user enters -1
                continue
            if 1 <= num <= 10000:#confirm that the user is actually entering a number from 1 to 10000.
                if isPrime(num):
                    print(str(num) + ' is prime')
                if isComposite(num):
                    print(str(num) + ' is composite')
                if isPerfect(num):
                    print(str(num) + ' is perfect')
                if isAbundant(num):
                    print(str(num) + ' is abundant')
                if isTriangular(num):
                    print(str(num) + ' is triangular')
                if isPentagonal(num):
                    print(str(num) + ' is pentagonal')
                if isHexagonal(num):
                    print(str(num) + ' is hexagonal')
                if isNarcissistic(num):
                    print(str(num) + ' is narcissistic')#if the number the user enters has any property of the functions above,return True
            else:
                print('Sorry, the input is not in range. Please try again.')#if the user enters a number not within 1-10000,print a warning
        except ValueError:
            print('Sorry, the input is not an int. Please try again.')#if the user doesn't enter an integer,print a warning

if __name__ == '__main__':
    main()