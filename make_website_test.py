import unittest

from make_website import *

class Make_Website_Test(unittest.TestCase):

    def test_reading_file(self):
        pass

    def test_detecting_name(self):
        lines = ['tony Stark']
        self.assertRaises(RuntimeError, detecting_name, lines)
        lines[0] = ' Tony Stark '
        self.assertEqual(detecting_name(lines), 'Tony Stark')
        lines[0] = ' Tony stark '
        self.assertEqual(detecting_name(lines), 'Tony stark')

    def test_detecting_email(self):
        lines = [' Tony Stark ']
        self.assertEqual(detecting_email(lines), '')
        lines.append('lbrandon@wharton2.upenn.com')
        self.assertEqual(detecting_email(lines), '')
        lines[1] = 'lbrandon2@wharton.upenn.com'
        self.assertEqual(detecting_email(lines), '')
        lines[1] = 'lbrandon@Wharton.upenn.com'
        self.assertEqual(detecting_email(lines), '')
        lines[1] = 'lbrandon@wharton.upenn.org'
        self.assertEqual(detecting_email(lines), '')
        lines[1] = ' lbrandon@wharton.upenn.com '
        self.assertEqual(detecting_email(lines), 'lbrandon@wharton.upenn.com')

    def test_detecting_courses(self):
        lines = [' Tony Stark ', ' lbrandon@wharton.upenn.com ', '   Courses  :-6324 CIT590, CIT591, CIT592   ']
        self.assertEqual(detecting_courses(lines), 'CIT590, CIT591, CIT592')

    def test_detecting_projects(self):
        lines = [' Tony Stark ', 'Projects ', ' CancerDetector ', ' Biomedical Imaging ', ' ', '---------- ', 'Math']
        self.assertEqual(detecting_projects(lines), ['CancerDetector', 'Biomedical Imaging'])

    def test_writing_HTML(self):
        pass

    def test_surround_block(self):
        self.assertEqual(surround_block('h1', 'text'), '<h1>text</h1>')
        self.assertRaises(TypeError, surround_block, 58, 'r6')
        self.assertRaises(TypeError, surround_block, 'dmc', 6324)

    def test_create_email_link(self):
        email_address = 'tom[at]seas.upenn.edu'
        self.assertEqual(create_email_link(email_address), 'tom[at]seas.upenn.edu')
        email_address = 'tom@seas.upenn.edu'
        self.assertEqual(create_email_link(email_address), '<a href="mailto:tom@seas.upenn.edu">\n'
                                                           'tom[aT]seas.upenn.edu</a>')



unittest.main()
