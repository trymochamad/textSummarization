import os.path,subprocess
from flask import Flask, request, abort
from argparse import ArgumentParser
from subprocess import STDOUT,PIPE

app = Flask(__name__)
def summary_process(filename, saved_filename, result_file, csv_file):
    print("try")
    subprocess.call(['java', '-jar', 'RingkasanKhusus.jar', filename, saved_filename, result_file, csv_file])
    return "Hello world"

@app.route("/summarize", methods=['POST'])
def execute_summary():
    body = request.json
    with open("hasilEkstrakKalimat.txt", "w") as file:
        for item in body:
            file.writelines(item["text"]+"\n")
    return summary_process('m0002-0_edit.xlsx', 'hasilKalimat.txt', 'hasilKlasifikasi.txt', 'prediksi.csv')

if __name__ == "__main__":
    arg_parser = ArgumentParser(
        usage='Usage: python ' + __file__ + ' [--port <port>] [--help]'
    )
    arg_parser.add_argument('-p', '--port', default=8000, help='port')
    arg_parser.add_argument('-d', '--debug', default=False, help='debug')
    options = arg_parser.parse_args()

    # create tmp dir for download content
    app.run(debug=options.debug, port=options.port)


# import os.path,subprocess
# from subprocess import STDOUT,PIPE

# def compile_java(java_file):
#     subprocess.check_call(['javac', java_file], shell = True) 

# def execute_java(java_file):

#   java_class,ext = os.path.splitext(java_file)
#   cmd = ['java', java_class]
#   proc = subprocess.Popen(cmd,stdout=PIPE,stderr=STDOUT, shell = True)
#   input=subprocess.Popen(cmd,stdin=PIPE)
    # print proc.stdout.read()



# def execute_java(java_file, stdin):
#     java_class,ext = os.path.splitext(java_file)
#     cmd = ['java', java_class]
#     proc = subprocess.Popen(cmd, stdin=PIPE, stdout=PIPE, stderr=STDOUT, c )
#     # stdout = 
#     stdout,stderr = proc.communicate(stdin)
#     print ('This was "' + stdout + '"')

# def execute_something():
#   proc = subprocess.check_output(["echo", "Hello World!"], shell = True)
#   print (proc)

# compile_java('Hi.java')
# execute_java('Hi.java')
# execute_something()

# class Command( object ):
#     def __init__( self, text ):
#         self.text = text
#     def execute( self ):
#         self.proc= subprocess.Popen( ... self.text ... )
#         self.proc.wait()

# class CommandSequence( Command ):
#     def __init__( self, *steps ):
#         self.steps = steps
#     def execute( self ):
#         for s in self.steps:
#             s.execute()