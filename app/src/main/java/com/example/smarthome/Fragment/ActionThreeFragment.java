package com.example.smarthome.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ActionThreeFragment extends Fragment {

    private Context context;
    private View view;

    private ImageButton btnRestart;
    private ImageButton btnAeration;
    private ImageButton btnLight;
    private ImageButton btnSocket;
    private ImageButton btnJiaShiQi;
    private ImageButton btnCurtain;
    private ImageButton btnDoor;
    private ImageButton btnAir;
    private ImageButton btnWater;

    private int fanProgress = 0;
    private int windowProgress = 0;
    private int lightOneProgress = 0;
    private int lightTwoProgress = 0;

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    public void initView(){
        btnRestart = view.findViewById(R.id.action_three_btn_restart);
        btnAeration = view.findViewById(R.id.action_three_btn_aeration);
        btnLight = view.findViewById(R.id.action_three_btn_light);
        btnSocket = view.findViewById(R.id.action_three_btn_socket);
        btnJiaShiQi = view.findViewById(R.id.action_three_btn_jiashiqi);
        btnCurtain = view.findViewById(R.id.action_three_btn_curtain);
        btnDoor = view.findViewById(R.id.action_three_btn_door);
        btnAir = view.findViewById(R.id.action_three_btn_air);
        btnWater = view.findViewById(R.id.action_three_btn_flower);
    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.action_three_btn_restart:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","CMD~PCAPP~Reset*\\r\\n");
                                String  s = "CMD~PCAPP~Reset*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"重启",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_three_btn_aeration:
                    AlertDialog.Builder fanBuilder = new AlertDialog.Builder(getActivity());
                    View viewFan = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog_fan,null);

//                    final int[] progress = {0};
//                    final int p;
                    SeekBar seekBarFan = viewFan.findViewById(R.id.sb_normal);
                    final TextView textViewFan = viewFan.findViewById(R.id.fan_txt);
                    seekBarFan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                          //  textView.setLeft(i);
                            textViewFan.setText(i + " % ");
//                            progress[0] = i;
//                            p = i;
                            fanProgress = i;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","触碰SeekBar");
                           // Toast.makeText(context, "触碰SeekBar", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","放开SeekBar");
                           // Toast.makeText(context, "放开SeekBar", Toast.LENGTH_SHORT).show();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~Fan-"+fanProgress+"%*\\r\\n");
                                        String  s = "CMD~PCAPP~Fan-"+fanProgress+"%*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                           // Toast.makeText(context,"换气",Toast.LENGTH_SHORT).show();
                        }
                    });

                    fanBuilder
                            .setMessage("风扇转速调节")
                            .setView(viewFan)
                            .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Toast.makeText(ManagementActivtiy.this,"已经修改",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                           .show();
                    break;
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","Aeration");
//                                String s = "Aeration";
//                                printWriter.println(s);
//                                printWriter.flush();
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"换气",Toast.LENGTH_SHORT).show();
                  //  break;

                case R.id.action_three_btn_light:
                    AlertDialog.Builder lightBuilder = new AlertDialog.Builder(getActivity());
                    View viewLight = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog_light,null);

                    SeekBar seekBarLightOne = viewLight.findViewById(R.id.sb_light_one);
                    SeekBar seekBarLightTwo = viewLight.findViewById(R.id.sb_light_two);
                    final TextView textViewLightOne = viewLight.findViewById(R.id.txt_light_one);
                    final TextView textViewLightTwo = viewLight.findViewById(R.id.txt_light_two);

                    seekBarLightOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            textViewLightOne.setText(i +"%");
                            lightOneProgress = i;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","触碰SeekBar");
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","放开SeekBar");
                            // Toast.makeText(context, "放开SeekBar", Toast.LENGTH_SHORT).show();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~Light1-"+lightOneProgress+"%*\\r\\n");
                                        String  s = "CMD~PCAPP~Light1-"+lightOneProgress+"%*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    });
                    seekBarLightTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            textViewLightTwo.setText(i +"%");
                            lightTwoProgress = i;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","触碰SeekBar");
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","放开SeekBar");
                            // Toast.makeText(context, "放开SeekBar", Toast.LENGTH_SHORT).show();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~Light2-"+lightTwoProgress+"%*\\r\\n");
                                        String  s = "CMD~PCAPP~Light2-"+lightTwoProgress+"%*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    });
                    lightBuilder
                            .setMessage("灯光亮度调节")
                            .setView(viewLight)
                            .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Toast.makeText(ManagementActivtiy.this,"已经修改",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .show();
                    break;

                case R.id.action_three_btn_socket:
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                    View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog_socket,null);
                    Button btnSocket1 = view2.findViewById(R.id.dialog_btn_socket1);
                    Button btnSocket2 = view2.findViewById(R.id.dialog_btn_socket2);
                    btnSocket1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","Socket1");
                                        String s = "CMD~PCAPP~Socket1*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"插座1",Toast.LENGTH_SHORT).show();
                            //break;
                        }
                    });
                    btnSocket2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","Socket2");
                                        String s = "CMD~PCAPP~Socket2*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"插座2",Toast.LENGTH_SHORT).show();
                           // break;
                        }
                    });
                    builder2.setView(view2)
                            .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Toast.makeText(ManagementActivtiy.this,"已经修改",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                           .show();
                    break;

                case R.id.action_three_btn_jiashiqi:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Humidification");
                                String s = "CMD~PCAPP~Humi*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"加湿",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_three_btn_curtain:
                    AlertDialog.Builder windowBuilder = new AlertDialog.Builder(getActivity());
                    View viewWindow = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog_fan,null);

//                    final int[] progress = {0};
//                    final int p;
                    SeekBar seekBarWindow = viewWindow.findViewById(R.id.sb_normal);
                    final TextView textViewWindow = viewWindow.findViewById(R.id.fan_txt);
                    seekBarWindow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            textViewWindow.setText(i + " % ");
                            windowProgress = i;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","触碰SeekBar");
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.e("TAG","放开SeekBar");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~Cur-"+windowProgress+"%*\\r\\n");
                                        String  s = "CMD~PCAPP~Cur-"+windowProgress+"%*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    });

                    windowBuilder
                            .setMessage("窗帘调节")
                            .setView(viewWindow)
                            .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Toast.makeText(ManagementActivtiy.this,"已经修改",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .show();
                  //  Toast.makeText(context,"窗帘",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_three_btn_door:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Door");
                                String  s = "CMD~PCAPP~Door*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"门",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_three_btn_air:


                    //Toast.makeText(context,"门",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                    View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog_air,null);
                    ImageButton btnAirSwitch = view3.findViewById(R.id.dialog_btn_kongtiaokaiguan);
                    Button btnTurnUpTemperature = view3.findViewById(R.id.dialog_btn_tigaowendu);
                    Button btnTurnDownTemperature = view3.findViewById(R.id.dialog_btn_jiangdiwendu);
                    Button btnComfortTemperature = view3.findViewById(R.id.dialog_btn_shushiwendu);
                    Button btnCoolMode = view3.findViewById(R.id.dialog_btn_coolmode);
                    Button btnWindMode = view3.findViewById(R.id.dialog_btn_windmode);
                    Button btnMoistMode = view3.findViewById(R.id.dialog_btn_moistmode);
                    Button btnWarmMode = view3.findViewById(R.id.dialog_btn_warmmode);
//                    Button btnAutoMode = view3.findViewById(R.id.dialog_btn_automode);
                    Button btnStrengthMode = view3.findViewById(R.id.dialog_btn_enlargemode);

                    btnAirSwitch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","Air");
                                        String  s = "CMD~PCAPP~AirCon*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    });
                    btnTurnUpTemperature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~AddTM*\\r\\n");
                                        String  s = "CMD~PCAPP~AddTM*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"提高温度",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnTurnDownTemperature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","ReduceTM");
                                        String  s = "CMD~PCAPP~ReduTM*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"降低温度",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnComfortTemperature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","ComfortTM");
                                        String  s = "CMD~PCAPP~ComTM*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"舒适温度",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnCoolMode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","Refrige");
                                        String  s = "CMD~PCAPP~Refri*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"制冷模式",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnWindMode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","AirSupply");
                                        String  s = "CMD~PCAPP~Supply*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"送风模式",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnMoistMode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","Dehumi");
                                        String  s = "CMD~PCAPP~DeHumi*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"除湿模式",Toast.LENGTH_SHORT).show();
                        }
                    });
                    btnWarmMode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","MakeHeat");
                                        String  s = "CMD~PCAPP~Warmup*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"制暖模式",Toast.LENGTH_SHORT).show();
                        }
                    });
//                    btnAutoMode.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Log.e("TAG","AUTO");
//                                        String  s = "AUTO";
//                                        printWriter.println(s);
//                                        printWriter.flush();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }).start();
//
//                            Toast.makeText(context,"自动模式",Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    btnStrengthMode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.e("TAG","CMD~PCAPP~AddWind*\\r\\n");
                                        String  s = "CMD~PCAPP~AddWind*\\r\\n";
                                        printWriter.print(s);
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(context,"增大风速",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder3.setView(view3)
                            .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   // Toast.makeText(ManagementActivtiy.this,"已经修改",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                           .show();
                    break;

                case R.id.action_three_btn_flower:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","CMD~PCAPP~Water*\\r\\n");
                                String  s = "CMD~PCAPP~Water*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"浇水",Toast.LENGTH_SHORT).show();
                    break;

                    default:
                        break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_action_three,container,false);
        initView();
        this.context = getActivity();
        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        btnRestart.setOnClickListener(new OnClick());
        btnAeration.setOnClickListener(new OnClick());
        btnLight.setOnClickListener(new OnClick());
        btnSocket.setOnClickListener(new OnClick());
        btnJiaShiQi.setOnClickListener(new OnClick());
        btnCurtain.setOnClickListener(new OnClick());
        btnDoor.setOnClickListener(new OnClick());
        btnAir.setOnClickListener(new OnClick());
        btnWater.setOnClickListener(new OnClick());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
