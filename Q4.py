x = input('give me an integer')#set x to the value the user enters
try:#Try to cast the input
    x = int(x)#if the value the user enters is an interger,cast x into an interger
    if abs(x - 100) <= 10:#to see if the integer is within 10 of 100, if so, print 'Yes'
        print('Yes')
    elif abs(x - 200) <= 10:#to see if the integer is within 10 of 200,if so,print 'Yes'
        print('Yes')
    else:#if the integer is not within 10 of 100 or 200,print 'No'
        print('No')
except ValueError as e:
    print('please input an integer')#if x is not an integer, print a warning