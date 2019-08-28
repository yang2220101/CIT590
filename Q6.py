x = input('give me an integer')#set x to the value the user enters
y = input('give me another integer')#set y to the value the user enters
try:#Try to cast the input
    x = int(x)
    y = int(y)#cast x and y into integers
    if (30 <= x <= 40) & (30 <= y <= 40):
        print('Yay')#to see if both values are in the range 30 to 40,if so,print 'Yay'
    elif (40 < x <= 50) & (40 < y <= 50):
        print('Yay')#to see if both values are in the range 40 to 50,if so,print 'Yay'
    else:
        print('Nay')#to see if both values are not in the range 30 to 40 or the range of 40 to 50,if so,print 'Nay'
except ValueError as e:
    print('please input integers')# if x or y are not integers, print a warning