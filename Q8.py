x = input('input temperature')#set x to the value of temperature the user enters
y = input('input wind speed')#set y to the value of wind speed the user enters
try:#Try to cast the input
    x = float(x)
    y = float(y)#cast x and y into floats
    if (x < 50) & (y > 5):
        T = round(35.74 + 0.6215 * x - 35.75 * y **0.16 + 0.4275 * x * y **0.16,2)
        print('the windchill temperature is ', T)#if temperatures below 50F and wind speed above 5 mph,calculate windchill
    elif x >= 50:
        print('it does not make sense to calculate wind chill:too hot')#if temperature for which the formula is not applicable,print a warning
    elif y <= 5:
        print('it does not make sense to calculate wind chill:no wind')#if wind speed for which the formula is not applicable,print a warning
except ValueError as e:
    print('please input floats')#if numbers are not both floats,print a warning