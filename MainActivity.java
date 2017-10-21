public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName;
    TextView textView;
    Database controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText)findViewById(R.id.firstName_input);
        lastName = (EditText)findViewById(R.id.lastName_input);
        textView = (TextView)findViewById(R.id.textView);


       controller = new Database(this," ",null,1);  //controller is an object of Database class.

    }
    public void btn_click(View view) {
        switch (view.getId()){

            case R.id.btn_add:
                try {
                    controller.insert_Student(firstName.getText().toString(),lastName.getText().toString());
                    Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(this,"Already exisist",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btn_delete:
                controller.delete_student(firstName.getText().toString());
                break;

            case R.id.btn_update:
                AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
                d.setTitle("Enter new first name");

                final EditText new_firstName = new EditText(this);
                d.setView(new_firstName);

                d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(firstName.getText().toString(),new_firstName.getText().toString());
                    }
                });
                d.show();
                break;

            case R.id.btn_list:
                controller.list_all_student(textView );
                firstName.setText("");
                lastName.setText("");
                break;
        }

    }
}
