from gtts import gTTS 
import speech_recognition as sr
import playsound
import sys

counter = 0

def myCommand():
    r = sr.Recognizer()
    global counter
    if counter == 0:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/What_Can_I_Do.mp3')
    with sr.Microphone() as source:
        counter += 1
        print('I am ready for your next command')
        r.pause_threshold = 1
        r.adjust_for_ambient_noise(source, duration=1)
        audio = r.listen(source)
    try: 
        command = r.recognize_google(audio)
        print('You said ' + command + '\n')

    #loop back in case not understood
    except sr.UnknownValueError:
        print("I didn't hear that")
        command = ""
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Go_Ahead.mp3')
        assistant(myCommand())

    return command

#if statements for executing commands
def assistant(command):
    if 'introduce yourself' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/KITT_Intro.mp3')
    if 'good night' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Goodnight.mp3')
        sys.exit(0)
    if 'music' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/What_Would_You_Like_To_Hear.mp3')
        #r = sr.Recognizer()
        #with sr.Microphone() as source:
        #    r.pause_threshold = 1
        #    r.adjust_for_ambient_noise(source, duration=1)
        #    audio = r.listen(source)
        #try: 
        #    command = r.recognize_google(audio)
        #    playsound.playsound('C:/Users/Alya/Music/' + command.Capitalize() + '.mp3')
        #except sr.UnknownValueError:
        #      assistant(myCommand())

        #return command

    if 'don\'t go' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Where_Would_I_Go.mp3')
    if 'stress' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Good_Vital_Signs.mp3')
    if 'what should I do' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Deny_Everything.mp3')  
    if 'don\'t you think' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/I_Suppose_So.mp3')
    if 'could you' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Ok.mp3')
    if 'going to the bar' in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Business_Not_Pleasure.mp3')
    if "I don\'t know what to do" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Alright_To_Respond.mp3')
    if "I'm sorry what can I do" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/A_Little_Consideration_Would_Be_A_Beginning.mp3')
    if "you're useless" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/If_It_Weren\'t_For_Me_You\'d_Be_Walking.mp3')
    if "frustrated" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/There\'s_No_Reason_For_Increased_Volume.mp3')
    if "why aren't you talking" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/We_Aren\'t_Speaking.mp3')
    if "mistake" in command:
        playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/We\'re_Only_Human.mp3')
while True:
    assistant(myCommand())


#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/A_Little_Consideration_Would_Be_A_Beginning.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Business_Not_Pleasure.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/KITT_Intro.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Alright_To_Respond.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Deny_Everything.mp3')  
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Good_Vital_Signs.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/I_Suppose_So.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Goodnight.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/If_It_Weren\'t_For_Me_You\'d_Be_Walking.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/ Audio_Clips/Ok.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/There\'s_No_Reason_For_Increased_Volume.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/We_Aren\'t_Speaking.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/We\'re_Only_Human.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/What_Can_I_Do.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/What_Would_You_Like_To_Hear.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Where_Would_I_Go.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/You_Really_Do_Care.mp3')

#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/All_Clear.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Back_Up_Space_Behind_You.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Go_Ahead.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/I_Deal_Solely_Empirical_Data.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/I_Dont_Know_What_Id_Do_Without_You.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/I_Prefer_To_Find_My_Own_Place.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Its_Safe_To_Proceed.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Ive_Already_Made_The_Inquiry.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Not_True.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Perfect.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Please_Be_Precise.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Thats_Not_Necessary.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/The_Little_Computer_Gadget.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/The_Little_Computer_Gadget_P2.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Unfortunately_Response_Delayed.mp3')
#playsound.playsound('C:/Users/Alya/Desktop/KITT/Audio_Clips/Waiting_In_Parking_Lot.mp3')

