def reading_file(file):
    """
    Reads the file and stores it in the program’s memory.
    """
    f = open(file)  # Opens the file for reading
    lines = f.readlines()  # read lines of the file as a list
    f.close()  # close the file
    return lines

def detecting_name(lines):
    """
    Extract the first line of the file, which contains name
    """
    name = lines[0].strip()  # extract the first line of the file
    first_name = name.split()  # split the name
    if first_name[0].istitle():  # to see if the first character in the name string is an uppercase letter
        return name
    else:
        raise RuntimeError('The first line has to be a name with proper capitalization!')



def detecting_email(lines):
    """
    Extract the a line that has the ‘@’ character, which contains email
    If the email is missing or inconsistent with the given rules, return an empty string
    """
    email = ''  # If an e-mail string is not found based on the given rules, return an empty string
    for line in lines:
        line = line.strip()  # get rid of the leading or trailing whitespace
        if '@' in line:  # find the line of email
            if line[-4:] != '.com' and line[-4:] != '.edu':  # check the last four characters
                return email


            head_at = line[line.find('@') + 1]  # check if it begins with a normal lowercase English character after '@'
            if head_at.isupper():
                return email


            for i in line:  # check if there are any digits or numbers
                if i.isdigit():
                    return email


            email = line  # if it passes the rules, get the email

    return email






def detecting_courses(lines):
    """
    Look for the word “Courses” in the file and then extract the line that contains that word.
    """
    for line in lines:
        if 'Courses' in line:  # find the line of courses
            line = line.strip()  # get rid of the leading or trailing whitespace
            for i in line[7:]:  # start searching after the word "Courses"
                if i.isalpha():  # courses start with with a letter of the English alphabet.
                    index = line[7:].find(i)
                    break
            courses = line[index + 7:]  # store the courses as a string
    return courses


def detecting_projects(lines):
    """
    Look for the word “Projects” in the file.
    Each subsequent line is a project, until a line ‘----------’.
    """
    pro_lst = []
    for line in lines:
        if 'Projects' in line:  # find the line of projects
            for i in lines[lines.index(line) + 1:]:  # each subsequent line is a project
                if '----------' not in lines[lines.index(i)]:  # stop at the line of '----------'
                    i = i.strip()
                    if len(i) != 0:  # check if there is a blank line
                        pro_lst.append(i)  # store the projects as a list
                else:
                    break
    return pro_lst



def writing_HTML(html, txt):
    """
    To programmatically write HTML
    """
    fin = open(html, 'r+')  # Open and read resume-template.html
    lines = fin.readlines()
    lines.pop()
    lines.pop()  # Remove the last 2 lines of HTML
    fout = open('resume.html', 'w')
    fout.writelines(lines)  # Write the final HTML to a new file resume.html


    # basic information section
    fout.write('<div id = "page-wrap">')  # make sure that all the text is enclosed
    txt = reading_file(txt)
    name = surround_block('h1', detecting_name(txt))
    email_address = create_email_link(detecting_email(txt))
    email = surround_block('p', 'Email: ' + email_address)
    fout.write(surround_block('div', '\n' + name + '\n' + email + '\n'))


    # projects section
    pro_lst = detecting_projects(txt)
    projects = []
    for i in pro_lst:
        i = surround_block('li', i)
        projects.append(i)
    projects = '\n'.join(projects)
    projects = surround_block('ul', '\n' + projects + '\n')
    pro_head = surround_block('h2', 'Projects')
    fout.write(surround_block('div', '\n' + pro_head + '\n' + projects + '\n'))


    # courses section
    courses = detecting_courses(txt)
    courses = surround_block('spam', courses)
    cou_head = surround_block('h3', 'Courses')
    fout.write(surround_block('div', '\n' + cou_head + '\n' + courses + '\n'))


    # close the HTML and files
    fout.write('\n</div>\n</body>\n</html>')
    fin.close()
    fout.close()


def surround_block(tag, text):
    """
    This function surrounds the given text with the given HTML tag and returns the string.
    """
    if type(tag) != str or type(text) != str:
        raise TypeError('tag and text must be string!')  # check if the parameters are strings
    return '<' + tag + '>' + text + '</' + tag + '>'

def create_email_link(email_address):
    """
    This function creates an email link with the given email_address
    """
    if '@' in email_address:
        head = email_address[0:email_address.find('@')]
        tail = email_address[email_address.find('@') + 1:]  # split the email into two parts divided by '@'
        email_address = '<a href="mailto:' + email_address + '">\n' + head + '[aT]' + tail + '</a>'
    return email_address

def main():
    writing_HTML('resume_template.html', 'resume.txt')


if __name__ == '__main__':
    main()
