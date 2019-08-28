x = input('give me a positive integer')#set x to the value the user enters
try:#Try to cast the input
    x = int(x)#if the value the user enters is a positive interger,cast x into an interger
    if x <= 0:#if the value the user enters is a negative interger,print a warning
        print('please input an positive integer')
    else:
        if (x%3 == 0) & (x%5 != 0):#to see if the number is divisible by 3, if so, print 'Fizz'
            print('Fizz')
        elif (x%5 == 0) & (x%3 != 0):#to see if the number is divisible by 5,if so,print 'Buzz'
            print('Buzz')
        elif (x%3 == 0) & (x%5 == 0):#to see if the number is divisible by 5 and 3,if so,print 'Fizz Buzz'
            print('Fizz Buzz')
        else:  # if the number is not divisible by 5 or 3,print itself
            print(x)
except ValueError as e:
    print('please input an positive integer')# if x is not a positive integer, print a warning